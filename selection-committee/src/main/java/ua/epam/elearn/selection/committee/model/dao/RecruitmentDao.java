package ua.epam.elearn.selection.committee.model.dao;

import ua.epam.elearn.selection.committee.model.entity.Application;
import ua.epam.elearn.selection.committee.model.entity.Faculty;
import ua.epam.elearn.selection.committee.model.entity.Recruitment;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface RecruitmentDao {

    Recruitment getRecruitmentById(long id);

    Recruitment getRecruitmentByApplicationId(long applicationId);

    List<Recruitment> getAllRecruitmentsByFacultyId(long facultyId);

    List<Recruitment> getNowOpenedRecruitmentsByFacultyId(long facultyId);

    List<Recruitment> getAllRecruitments();

    List<Recruitment> getAllOpenedOverdueRecruitments();

    int getCountOfFacultiesByFilter(String[] filters);

    Map<Recruitment, Faculty> getPaginationAllRecruitmentsWithFaculties(String[] filters, String order, int limit, int offset);

    boolean addRecruitment(Recruitment recruitment);

    boolean closeRecruitment(long recruitmentId);

    boolean updateRecruitmentDate(long recruitmentId, LocalDate date);

    Application getRecruitmentApplicationStatusByUserId(long recruitmentId, long userId);

}
