package ua.epam.elearn.selection.committee.model.dao.impl;

import ua.epam.elearn.selection.committee.model.dao.ApplicationDao;
import ua.epam.elearn.selection.committee.model.dao.database.DBManager;
import ua.epam.elearn.selection.committee.model.dao.impl.queries.UserSQLQueries;
import ua.epam.elearn.selection.committee.model.dao.mapper.ApplicationMapper;
import ua.epam.elearn.selection.committee.model.dao.mapper.SubjectMapper;
import ua.epam.elearn.selection.committee.model.entity.Application;
import ua.epam.elearn.selection.committee.model.entity.Grade;
import ua.epam.elearn.selection.committee.model.entity.Subject;
import ua.epam.elearn.selection.committee.model.entity.enums.ApplicationState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDaoImpl implements ApplicationDao {

    private static final String CREATE_NEW_APPLICATION =
            "INSERT INTO application (user_id, recruitment_id, application_state_id) values (?, ?, ?)";

    private static final String INSERT_SUBJECT_GRADES =
            "INSERT INTO subject_user_grades (application_id, subject_id, grade) VALUES (?,?,?)";

    private static final String SELECT_APPLICATION_BY_USER_AND_RECRUITMENT =
            "SELECT * FROM application\n" +
                    "JOIN \"user\" as \"user\"  on application.user_id = \"user\".id\n" +
                    "JOIN recruitment on application.recruitment_id = recruitment.id\n" +
                    "WHERE application.user_id = ? AND application.recruitment_id = ?";

    private static final String SELECT_APPLICATION_BY_ID =
            "SELECT * FROM application\n" +
                    "JOIN \"user\" as \"user\"  on application.user_id = \"user\".id\n" +
                    "JOIN recruitment on application.recruitment_id = recruitment.id\n" +
                    "WHERE application.id = ?";

    private static final String SELECT_APPLICATIONS_BY_USER =
            "SELECT * FROM application\n" +
                    "JOIN \"user\" as \"user\"  on application.user_id = \"user\".id\n" +
                    "JOIN recruitment on application.recruitment_id = recruitment.id\n" +
                    "WHERE application.user_id = ?";

    private static final String SELECT_APPLICATIONS_BY_RECRUITMENT =
            "SELECT *,\n" +
                    "       (SELECT sum(subject_user_grades.grade)\n" +
                    "        FROM subject_user_grades\n" +
                    "        WHERE subject_user_grades.application_id = application.id) AS grade\n" +
                    "FROM application\n" +
                    "         JOIN \"user\" as \"user\" on application.user_id = \"user\".id\n" +
                    "         JOIN recruitment on application.recruitment_id = recruitment.id\n" +
                    "WHERE application.recruitment_id = ?\n" +
                    "ORDER BY grade DESC";

    private static final String SELECT_GRADES_BY_APPLICATION =
            "SELECT * FROM subject\n" +
                    "JOIN subject_user_grades  on subject.id = subject_user_grades.subject_id\n" +
                    "WHERE subject_user_grades.application_id = ?";

    private static final String UPDATE_APPLICATION_STATE =
            "UPDATE application\n" +
                    "SET application_state_id = ?\n" +
                    "WHERE id = ?";

    @Override
    public long createApplication(Application application) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(CREATE_NEW_APPLICATION, new String[]{"id"})) {

            stmt.setLong(1, application.getUser().getId());
            stmt.setLong(2, application.getRecruitment().getId());
            stmt.setLong(3, application.getState().getId());

            stmt.executeUpdate();

            ResultSet resultSet = stmt.getGeneratedKeys();

            return resultSet.next() ? resultSet.getLong("id") : -1;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertGrades(List<Grade> gradeList) {
        for (Grade grade : gradeList) {

            try (Connection con = DBManager.getInstance().getConnection();
                 PreparedStatement stmt = con.prepareStatement(INSERT_SUBJECT_GRADES)) {

                stmt.setLong(1, grade.getApplicationId());
                stmt.setLong(2, grade.getSubjectId());
                stmt.setLong(3, grade.getGrade());

                stmt.execute();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean updateApplicationState(long applicationId, ApplicationState state) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_APPLICATION_STATE)) {

            stmt.setLong(1, state.getId());
            stmt.setLong(2, applicationId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Application getApplicationById(long id) {
        Application application = null;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_APPLICATION_BY_ID)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                ApplicationMapper applicationMapper = new ApplicationMapper();

                while (rs.next()) {
                    application = applicationMapper.extractFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return application;
    }

    @Override
    public Application getApplicationByUserIdAndRecruitmentId(long userId, long recruitmentId) {
        Application application = null;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_APPLICATION_BY_USER_AND_RECRUITMENT)) {
            stmt.setLong(1, userId);
            stmt.setLong(2, recruitmentId);

            try (ResultSet rs = stmt.executeQuery()) {
                ApplicationMapper applicationMapper = new ApplicationMapper();

                while (rs.next()) {
                    application = applicationMapper.extractFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return application;
    }

    @Override
    public List<Application> getAllApplications() {
        return null;
    }

    @Override
    public List<Application> getApplicationsByUserId(long userId) {
        return getApplicationsById(userId, SELECT_APPLICATIONS_BY_USER);
    }


    @Override
    public List<Application> getApplicationsByRecruitmentId(long recruitmentId) {
        return getApplicationsById(recruitmentId, SELECT_APPLICATIONS_BY_RECRUITMENT);
    }

    private List<Application> getApplicationsById(long id, String query) {
        List<Application> applications = new ArrayList<>();

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                ApplicationMapper applicationMapper = new ApplicationMapper();

                while (rs.next()) {
                    applications.add(applicationMapper.extractFromResultSet(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return applications;
    }


    @Override
    public List<Subject> getSubjectsByApplication(long applicationId) {
        List<Subject> subjects = new ArrayList<>();

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_GRADES_BY_APPLICATION)) {

            stmt.setLong(1, applicationId);

            try (ResultSet rs = stmt.executeQuery()) {
                SubjectMapper subjectMapper = new SubjectMapper();

                while (rs.next()) {
                    subjects.add(subjectMapper.extractSubjectWithGradesFromResultSet(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return subjects;
    }

}
