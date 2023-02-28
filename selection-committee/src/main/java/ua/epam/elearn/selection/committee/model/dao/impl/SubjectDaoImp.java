package ua.epam.elearn.selection.committee.model.dao.impl;

import ua.epam.elearn.selection.committee.model.dao.SubjectDao;
import ua.epam.elearn.selection.committee.model.dao.database.DBManager;
import ua.epam.elearn.selection.committee.model.dao.mapper.SubjectMapper;
import ua.epam.elearn.selection.committee.model.entity.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDaoImp implements SubjectDao {

    private static final String SELECT_ALL_SUBJECTS = "SELECT * FROM subject";
    private static final String SELECT_SUBJECT_BY_ID = "SELECT * FROM subject WHERE id=?";
    private static final String CREATE_SUBJECT = "INSERT INTO subject (name_en, name_ru, name_uk) values (?, ?, ?)";
    private static final String INSERT_REQUIRED_SUBJECTS = "INSERT INTO required_subject (faculty_id, subject_id) VALUES(?,?)";
    private static final String DELETE_REQUIRED_SUBJECTS = "DELETE FROM required_subject WHERE faculty_id = ?";
    private static final String SELECT_REQUIRED_SUBJECTS_BY_RECRUITMENT_ID =
            "SELECT subject.*\n" +
                    "FROM subject\n" +
                    "          JOIN required_subject ON required_subject.subject_id = subject.id\n" +
                    "          JOIN faculty ON required_subject.faculty_id = faculty.id\n" +
                    "          JOIN recruitment ON faculty.id = recruitment.faculty_id\n" +
                    "WHERE recruitment.id = ?";

    private static final String SELECT_REQUIRED_SUBJECTS_BY_FACULTY_ID =
            "SELECT subject.* " +
                    "FROM subject " +
                    "INNER JOIN required_subject " +
                    "ON required_subject.subject_id = subject.id " +
                    "WHERE required_subject.faculty_id = ?";

    @Override
    public boolean createSubject(Subject subject) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(CREATE_SUBJECT)) {

            stmt.setString(1, subject.getNameEn());
            stmt.setString(2, subject.getNameRu());
            stmt.setString(3, subject.getNameUk());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Subject getSubjectById(long id) {
        Subject subject = null;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_SUBJECT_BY_ID)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                SubjectMapper subjectMapper = new SubjectMapper();

                while (rs.next()) {
                    subject = subjectMapper.extractFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return subject;
    }


    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();

        try (Connection con = DBManager.getInstance().getConnection();
             Statement stmt = con.createStatement()) {

            try (ResultSet rs = stmt.executeQuery(SELECT_ALL_SUBJECTS)) {
                SubjectMapper subjectMapper = new SubjectMapper();
                while (rs.next()) {
                    subjects.add(subjectMapper.extractFromResultSet(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return subjects;
    }


    @Override
    public List<Subject> getRequiredSubjectsByFacultyId(long facultyId) {
        return getRequiredSubjectsById(facultyId, SELECT_REQUIRED_SUBJECTS_BY_FACULTY_ID);
    }

    @Override
    public List<Subject> getRequiredSubjectsByRecruitmentId(long recruitmentId) {
        return getRequiredSubjectsById(recruitmentId, SELECT_REQUIRED_SUBJECTS_BY_RECRUITMENT_ID);
    }

    private List<Subject> getRequiredSubjectsById(long facultyId, String query) {
        List<Subject> subjects = new ArrayList<>();

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setLong(1, facultyId);

            try (ResultSet rs = stmt.executeQuery()) {
                SubjectMapper subjectMapper = new SubjectMapper();

                while (rs.next()) {
                    subjects.add(subjectMapper.extractFromResultSet(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return subjects;
    }

    @Override
    public boolean addRequiredSubjects(long facultyId, List<Long> subjectList) {

        try (Connection con = DBManager.getInstance().getConnection()) {

            try (PreparedStatement stmt = con.prepareStatement(INSERT_REQUIRED_SUBJECTS)) {
                con.setAutoCommit(false);

                for (Long subjectId : subjectList) {
                    stmt.setLong(1, facultyId);
                    stmt.setLong(2, subjectId);
                    stmt.executeUpdate();
                }
                con.commit();
                con.setAutoCommit(true);

                return true;
            } catch (SQLException e) {
                con.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteRequiredSubjects(Long facultyId) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_REQUIRED_SUBJECTS)) {

            stmt.setLong(1, facultyId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
