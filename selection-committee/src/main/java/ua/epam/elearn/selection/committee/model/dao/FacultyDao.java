package ua.epam.elearn.selection.committee.model.dao;

import ua.epam.elearn.selection.committee.model.entity.Faculty;

import java.util.List;

public interface FacultyDao {

    Faculty getFacultyById(long id);

    Faculty getFacultyByRecruitmentId(long recruitmentId);

    Faculty getFacultyByName(String name);

    List<Faculty> getAllFaculties();

    List<Faculty> getPaginationAllFaculties(String order, int limit, int offset);

    List<Faculty> getAllFacultiesWhereOpenedRecruitments();

    boolean addFaculty(Faculty faculty);

    boolean isExistedByFacultyId(Long facultyId);

    boolean delete(Long id);

    boolean update(Faculty faculty);

    int getAllFacultiesSize();
}
