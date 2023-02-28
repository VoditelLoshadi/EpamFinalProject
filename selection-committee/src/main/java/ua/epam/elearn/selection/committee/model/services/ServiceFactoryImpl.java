package ua.epam.elearn.selection.committee.model.services;

import ua.epam.elearn.selection.committee.model.dao.DaoFactory;
import ua.epam.elearn.selection.committee.model.services.util.SHA256PasswordEncoder;

public class ServiceFactoryImpl extends ServiceFactory {

    @Override
    public UserService createUserService() {
        return new UserService(
                new SHA256PasswordEncoder(),
                DaoFactory.getInstance().createUserDao()
        );
    }

    @Override
    public SubjectService createSubjectService() {
        return new SubjectService(
                DaoFactory.getInstance().createSubjectDao()
        );
    }

    @Override
    public FacultyService createFacultyService() {
        return new FacultyService(
                DaoFactory.getInstance().createFacultyDao()
        );
    }

    @Override
    public RecruitmentService createRecruitmentService() {
        return new RecruitmentService(
                DaoFactory.getInstance().createRecruitmentDao()
        );
    }

    @Override
    public ApplicationService createApplicationService() {
        return new ApplicationService(
                DaoFactory.getInstance().createApplicationDao(),
                DaoFactory.getInstance().createFacultyDao()
        );
    }
}