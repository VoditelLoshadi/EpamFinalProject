package ua.epam.elearn.selection.committee.model.services;



import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.elearn.selection.committee.model.dao.ApplicationDao;
import ua.epam.elearn.selection.committee.model.dao.FacultyDao;
import ua.epam.elearn.selection.committee.model.dto.ApplicationDto;
import ua.epam.elearn.selection.committee.model.entity.*;
import ua.epam.elearn.selection.committee.model.entity.enums.ApplicationState;
import ua.epam.elearn.selection.committee.model.exception.user.UserAlreadyAppliedException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ApplicationService {

    Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    private final ApplicationDao applicationDao;
    private final FacultyDao facultyDao;

    public ApplicationService(ApplicationDao applicationDao, FacultyDao facultyDao) {
        this.applicationDao = applicationDao;
        this.facultyDao = facultyDao;
    }

    public List<Application> getAllApplicationsByUserId(long userId) {

        return applicationDao.getApplicationsByUserId(userId);
    }

    public void finalizeApplication(long recruitmentId) {
        MailService mailService = new MailService();

        List<Application> applicationList = getAllApplicationsByRecruitmentId(recruitmentId);
        Faculty faculty = facultyDao.getFacultyByRecruitmentId(recruitmentId);

        applicationList.sort(Comparator.comparingDouble(Application::getAverageGrade));

        applicationList.stream()
                .limit(faculty.getBudgetCapacity())
                .forEach(e -> {
                    applicationDao.updateApplicationState(e.getId(), ApplicationState.ACCEPTED_FOR_BUDGET);
                    mailService.sendBudgetMessage(e.getUser().getEmail());
                });

        applicationList.stream()
                .skip(faculty.getBudgetCapacity())
                .limit(faculty.getGeneralCapacity() - faculty.getBudgetCapacity())
                .forEach(e -> {
                    applicationDao.updateApplicationState(e.getId(), ApplicationState.ACCEPTED_FOR_CONTRACT);
                    mailService.sendContractMessage(e.getUser().getEmail());
                });

        applicationList.stream()
                .skip(faculty.getGeneralCapacity())
                .forEach(e -> {
                    applicationDao.updateApplicationState(e.getId(), ApplicationState.REJECTED);
                    mailService.sendBadMessage(e.getUser().getEmail());
                });

        logger.info("All applications in Recruitment ({}) was finalized", recruitmentId);

    }

    public List<Application> getAllApplicationsByRecruitmentId(long recruitmentId) {

        List<Application> applicationList = applicationDao.getApplicationsByRecruitmentId(recruitmentId);
        for (Application application : applicationList) {
            List<Subject> subjects = applicationDao.getSubjectsByApplication(application.getId());
            application.setSubjects(subjects);

            application.setAverageGrade(getAverageMark(subjects));
        }

        return applicationList;
    }

    public Application getApplication(long applicationId) {
        Application application = applicationDao.getApplicationById(applicationId);

        if (application != null) {
            List<Subject> subjects = applicationDao.getSubjectsByApplication(application.getId());
            application.setSubjects(subjects);
            application.setAverageGrade(getAverageMark(subjects));
        }

        return application;
    }

    private double getAverageMark(List<Subject> subjectList) {
        return subjectList.stream().mapToDouble(e -> e.getGrade().getGrade()).average().getAsDouble();
    }

    public void addNewApplication(ApplicationDto applicationDto, SubjectService subjectService) throws UserAlreadyAppliedException {

        applicationDto.setState(ApplicationState.REGISTERED.name());

        Application application = new Application(applicationDto);

        checkApplicationIsRegister(application.getUser().getId(), application.getRecruitment().getId());

        application.setId(applicationDao.createApplication(application));

        logger.info("Application ({}) has been created", application.getId());

        List<Grade> gradeList = parseToGradeList(applicationDto.getGrades(), subjectService.getRequiredSubjectsByRecruitmentId(application.getRecruitment().getId()), application);
        applicationDao.insertGrades(gradeList);

        logger.info("Grades ({}) added to application({}) successful", gradeList.toString(), application.getId());
    }

    private List<Grade> parseToGradeList(String[] grades, List<Subject> requiredSubjects, Application application) {

        List<Grade> gradesList = new ArrayList<>();
        Grade grade;
        for (int i = 0; i < grades.length; i++) {
            grade = new Grade.Builder().addGrade(Long.parseLong(grades[i])).addSubjectId(requiredSubjects.get(i).getId()).addApplicationId(application.getId()).build();

            gradesList.add(grade);
        }
        return gradesList;
    }

    private void checkApplicationIsRegister(long userId, long recruitmentId) throws UserAlreadyAppliedException {
        Application application = applicationDao.getApplicationByUserIdAndRecruitmentId(userId, recruitmentId);
        if (application != null) {
            logger.warn("Application ({}) already register ", application.getId());
            throw new UserAlreadyAppliedException();
        }
    }

    private void checkSubjectListIsFull(ApplicationDto applicationDto, SubjectService subjectService) throws UserAlreadyAppliedException {


        List<Subject> requiredSubjects = subjectService.getRequiredSubjectsByRecruitmentId(Long.parseLong(applicationDto.getRecruitmentId()));
        if (applicationDto.getGrades().length != requiredSubjects.size()) throw new UserAlreadyAppliedException();
    }
}
