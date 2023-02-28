package ua.epam.elearn.selection.committee.model.services;

import org.junit.Before;
import org.junit.Test;
import ua.epam.elearn.selection.committee.model.dao.ApplicationDao;
import ua.epam.elearn.selection.committee.model.dao.FacultyDao;
import ua.epam.elearn.selection.committee.model.dao.SubjectDao;
import ua.epam.elearn.selection.committee.model.dto.ApplicationDto;
import ua.epam.elearn.selection.committee.model.entity.*;
import ua.epam.elearn.selection.committee.model.entity.enums.ApplicationState;
import ua.epam.elearn.selection.committee.model.exception.user.UserAlreadyAppliedException;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationServiceTest {

    private final Long APPLICATION_ID = 12L;
    private final Long USER_ID = 12L;
    private final Long RECRUITMENT_ID = 15L;

    private ApplicationService applicationService;
    private SubjectService subjectService;

    private ApplicationDao applicationDao;
    private FacultyDao facultyDao;
    private SubjectDao subjectDao;

    private Faculty faculty;
    private Application application;
    private Subject subject;
    private ApplicationDto applicationDto;

    @Before
    public void setUp() throws Exception {
        applicationDao = mock(ApplicationDao.class);
        facultyDao = mock(FacultyDao.class);
        subjectDao = mock(SubjectDao.class);

        applicationService = new ApplicationService(applicationDao, facultyDao);
        subjectService = new SubjectService(subjectDao);

        faculty = new Faculty.Builder()
                .addId(100L)
                .addName("Faculty")
                .addBudgetCapacity(5)
                .addGeneralCapacity(15)
                .build();

        application = new Application.Builder()
                .addId(APPLICATION_ID)
                .addRecruitment(new Recruitment.Builder()
                        .addId(RECRUITMENT_ID)
                        .build())
                .addState(ApplicationState.REGISTERED)
                .addUser(new User.Builder()
                        .addId(USER_ID)
                        .addEmail("test@test.com")
                        .build())
                .addAverageGrade(200)
                .build();

        subject = new Subject.Builder()
                .addGrade(new Grade.Builder()
                        .addGrade(150)
                        .build())
                .build();

        applicationDto = new ApplicationDto(USER_ID.toString(),
                RECRUITMENT_ID.toString(),
                new String[]{"200"});
    }

    @Test
    public void checkFinalizeApplication() {
        when(facultyDao.getFacultyByRecruitmentId(RECRUITMENT_ID)).thenReturn(faculty);
        when(applicationDao.getApplicationsByRecruitmentId(RECRUITMENT_ID)).thenReturn(Arrays.asList(application));
        when(applicationDao.getSubjectsByApplication(APPLICATION_ID)).thenReturn(Arrays.asList(subject));
        when(applicationDao.updateApplicationState(APPLICATION_ID, ApplicationState.REJECTED)).thenReturn(true);
        when(applicationDao.updateApplicationState(APPLICATION_ID, ApplicationState.ACCEPTED_FOR_CONTRACT)).thenReturn(true);
        when(applicationDao.updateApplicationState(APPLICATION_ID, ApplicationState.ACCEPTED_FOR_BUDGET)).thenReturn(true);


        assertDoesNotThrow(() -> applicationService.finalizeApplication(RECRUITMENT_ID));
    }

    @Test
    public void checkGetApplication() {
        when(applicationDao.getApplicationById(APPLICATION_ID)).thenReturn(application);
        when(applicationDao.getSubjectsByApplication(APPLICATION_ID)).thenReturn(Arrays.asList(subject));

        assertNotNull(applicationService.getApplication(APPLICATION_ID));
    }


    @Test
    public void checkAddNewApplicationShouldNotThrowException() {
        when(applicationDao.getApplicationByUserIdAndRecruitmentId(USER_ID, RECRUITMENT_ID)).thenReturn(null);
        when(applicationDao.createApplication(application)).thenReturn(APPLICATION_ID);
        when(subjectDao.getRequiredSubjectsByRecruitmentId(RECRUITMENT_ID)).thenReturn(Arrays.asList(subject));

        assertDoesNotThrow(() -> applicationService.addNewApplication(applicationDto, subjectService));
    }

    @Test
    public void checkAddNewApplicationShouldThrowUserAlreadyAppliedException() {

        when(applicationDao.getApplicationByUserIdAndRecruitmentId(USER_ID, RECRUITMENT_ID)).thenReturn(application);

        assertThrows(
                UserAlreadyAppliedException.class,
                () -> applicationService.addNewApplication(applicationDto, subjectService)
        );
    }
}
