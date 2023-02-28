package ua.epam.elearn.selection.committee.model.dao;

import ua.epam.elearn.selection.committee.model.entity.Application;
import ua.epam.elearn.selection.committee.model.entity.Grade;
import ua.epam.elearn.selection.committee.model.entity.Subject;
import ua.epam.elearn.selection.committee.model.entity.enums.ApplicationState;

import java.util.List;

public interface ApplicationDao {

    long createApplication(Application application);

    void insertGrades(List<Grade> gradeList);

    boolean updateApplicationState(long applicationId, ApplicationState state);

    Application getApplicationById(long id);

    Application getApplicationByUserIdAndRecruitmentId(long userId, long recruitmentId);

    List<Application> getAllApplications();

    List<Application> getApplicationsByUserId(long userId);

    List<Subject> getSubjectsByApplication(long applicationId);

    List<Application> getApplicationsByRecruitmentId(long recruitmentId);

}
