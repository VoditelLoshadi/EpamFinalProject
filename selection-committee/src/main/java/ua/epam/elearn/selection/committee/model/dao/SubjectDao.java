package ua.epam.elearn.selection.committee.model.dao;

import ua.epam.elearn.selection.committee.model.entity.Subject;

import java.util.List;

public interface SubjectDao {

    boolean createSubject(Subject subject);

    Subject getSubjectById(long id);

    List<Subject> getAllSubjects();

    List<Subject> getRequiredSubjectsByFacultyId(long facultyId);

    List<Subject> getRequiredSubjectsByRecruitmentId(long recruitmentId);

    boolean addRequiredSubjects(long facultyId, List<Long> subjectList);

    boolean deleteRequiredSubjects(Long facultyId);


}
