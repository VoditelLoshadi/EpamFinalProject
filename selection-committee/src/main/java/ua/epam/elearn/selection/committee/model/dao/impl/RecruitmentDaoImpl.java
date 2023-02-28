package ua.epam.elearn.selection.committee.model.dao.impl;



import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.elearn.selection.committee.model.dao.RecruitmentDao;
import ua.epam.elearn.selection.committee.model.dao.database.DBManager;
import ua.epam.elearn.selection.committee.model.dao.impl.queries.FacultySQLQueries;
import ua.epam.elearn.selection.committee.model.dao.mapper.ApplicationMapper;
import ua.epam.elearn.selection.committee.model.dao.mapper.RecruitmentMapper;
import ua.epam.elearn.selection.committee.model.entity.Application;
import ua.epam.elearn.selection.committee.model.entity.Faculty;
import ua.epam.elearn.selection.committee.model.entity.Recruitment;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class RecruitmentDaoImpl implements RecruitmentDao {

    private final Logger logger = LoggerFactory.getLogger(RecruitmentDaoImpl.class);

    private static final String SELECT_ALL_RECRUITMENTS =
            "SELECT * FROM recruitment";

    private static final String SELECT_ALL_OPENED_OVERDUE_RECRUITMENTS =
            "SELECT * FROM recruitment\n" +
            "WHERE closed = false AND end_date < CURRENT_DATE";

    private static final String SELECT_ALL_OPENED_RECRUITMENTS =
            "SELECT * FROM recruitment\n" +
            "WHERE closed = false";

    private static final String SELECT_OPENED_RECRUITMENTS_BY_FACULTY_ID =
            "SELECT * FROM recruitment WHERE closed=false and faculty_id=? AND ? BETWEEN start_date AND end_date";
    private static final String SELECT_RECRUITMENT_BY_ID =
            "SELECT * FROM recruitment WHERE id=?";
    private static final String SELECT_RECRUITMENT_BY_APPLICATION_ID =
            "SELECT recruitment.* FROM recruitment\n" +
                    "JOIN application ON application.recruitment_id = recruitment.id\n" +
                    "WHERE application.id=?";
    private static final String SELECT_RECRUITMENTS_BY_FACULTY_ID =
            "SELECT * FROM recruitment WHERE faculty_id=?";
    private static final String CREATE_RECRUITMENT =
            "INSERT INTO recruitment (name, faculty_id, start_date, end_date) values (?, ?, ?, ?)";
    private static final String SELECT_APPLICATION_BY_RECRUITMENT_ID_AND_USER_ID =
            "SELECT * FROM application\n" +
                    "JOIN \"user\" ON \"user\".id = application.user_id\n" +
                    "JOIN recruitment ON recruitment.id = application.recruitment_id\n" +
                    "WHERE recruitment.id = ? AND \"user\".id = ?";
    public static final String CLOSE_RECRUITMENT_BY_ID = "UPDATE recruitment SET closed=true WHERE id = ?";
    public static final String UPDATE_RECRUITMENT_DATE_BY_ID = "UPDATE recruitment SET end_date=? WHERE id = ?";
    private static final String SELECT_ALL_RECRUITMENT_AND_FACULTIES =
            "SELECT * FROM recruitment\n" +
                    "JOIN faculty ON recruitment.faculty_id = faculty.id";
    @Override
    public Recruitment getRecruitmentById(long id) {
        return getRecruitmentByIdAndQuery(id, SELECT_RECRUITMENT_BY_ID);
    }

    @Override
    public Recruitment getRecruitmentByApplicationId(long applicationId) {
        return getRecruitmentByIdAndQuery(applicationId, SELECT_RECRUITMENT_BY_APPLICATION_ID);
    }

    private Recruitment getRecruitmentByIdAndQuery(long id, String query) {
        Recruitment recruitment = null;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                RecruitmentMapper recruitmentMapper = new RecruitmentMapper();

                while (rs.next()) {
                    recruitment = recruitmentMapper.extractRecruitmentFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return recruitment;
    }

    @Override
    public List<Recruitment> getAllRecruitments() {

        return getAllRecruitmentsByQuery(SELECT_ALL_RECRUITMENTS);
    }

    @Override
    public List<Recruitment> getAllOpenedOverdueRecruitments() {

        return getAllRecruitmentsByQuery(SELECT_ALL_OPENED_OVERDUE_RECRUITMENTS);
    }


    private List<Recruitment> getAllRecruitmentsByQuery(String query) {
        List<Recruitment> recruitments = new ArrayList<>();

        try (Connection con = DBManager.getInstance().getConnection();
             Statement stmt = con.createStatement()) {

            try (ResultSet rs = stmt.executeQuery(query)) {
                RecruitmentMapper recruitmentMapper = new RecruitmentMapper();

                while (rs.next()) {
                    recruitments.add(recruitmentMapper.extractRecruitmentFromResultSet(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return recruitments;
    }

    @Override
    public List<Recruitment> getAllRecruitmentsByFacultyId(long facultyId) {
        return getRecruitmentsByFacultyIdAndQuery(facultyId, SELECT_RECRUITMENTS_BY_FACULTY_ID);
    }

    @Override
    public List<Recruitment> getNowOpenedRecruitmentsByFacultyId(long facultyId) {
        List<Recruitment> recruitments = new ArrayList<>();

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_OPENED_RECRUITMENTS_BY_FACULTY_ID)) {

            stmt.setLong(1, facultyId);
            stmt.setDate(2, Date.valueOf(LocalDate.now()));

            try (ResultSet rs = stmt.executeQuery()) {
                RecruitmentMapper recruitmentMapper = new RecruitmentMapper();

                while (rs.next()) {
                    recruitments.add(recruitmentMapper.extractRecruitmentFromResultSet(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return recruitments;
    }

    private List<Recruitment> getRecruitmentsByFacultyIdAndQuery(long facultyId, String query) {
        List<Recruitment> recruitments = new ArrayList<>();

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setLong(1, facultyId);

            try (ResultSet rs = stmt.executeQuery()) {
                RecruitmentMapper recruitmentMapper = new RecruitmentMapper();

                while (rs.next()) {
                    recruitments.add(recruitmentMapper.extractRecruitmentFromResultSet(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return recruitments;
    }

    @Override
    public boolean addRecruitment(Recruitment recruitment) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(CREATE_RECRUITMENT)) {

            stmt.setString(1, recruitment.getName());
            stmt.setLong(2, recruitment.getFacultyId());
            stmt.setDate(3, Date.valueOf(recruitment.getStartDate()));
            stmt.setDate(4, Date.valueOf(recruitment.getEndDate()));

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public boolean closeRecruitment(long recruitmentId) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(CLOSE_RECRUITMENT_BY_ID)) {

            stmt.setLong(1, recruitmentId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateRecruitmentDate(long recruitmentId, LocalDate date) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_RECRUITMENT_DATE_BY_ID)) {

            stmt.setDate(1, Date.valueOf(date));
            stmt.setLong(2, recruitmentId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Application getRecruitmentApplicationStatusByUserId(long recruitmentId, long userId) {

        Application application = null;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_APPLICATION_BY_RECRUITMENT_ID_AND_USER_ID)) {
            stmt.setLong(1, recruitmentId);
            stmt.setLong(2, userId);

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
    public int getCountOfFacultiesByFilter(String[] filters) {
        return getQueryRowCount(SELECT_ALL_RECRUITMENT_AND_FACULTIES + getFilterQuery(filters));
    }

    @Override
    public Map<Recruitment, Faculty> getPaginationAllRecruitmentsWithFaculties(String[] filters, String order, int limit, int offset) {
        String query = SELECT_ALL_RECRUITMENT_AND_FACULTIES +
                getFilterQuery(filters) +
                getOrderByQuery(order) +
                getPaginationPageQuery(limit, offset);


        Map<Recruitment, Faculty> recruitments = new LinkedHashMap<>();

        try (Connection con = DBManager.getInstance().getConnection();
             Statement stmt = con.createStatement()) {

            try (ResultSet rs = stmt.executeQuery(query)) {
                RecruitmentMapper recruitmentMapper = new RecruitmentMapper();

                while (rs.next()) {
                    recruitments.put(recruitmentMapper.extractRecruitmentFromResultSet(rs),
                            recruitmentMapper.extractFacultyFromResultSet(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return recruitments;
    }

    private String getPaginationPageQuery(int limit, int offset) {
        return " LIMIT " + limit + " OFFSET " + offset + "\n";
    }

    private String getOrderByQuery(String order) {
        return " ORDER BY " + order + "\n";
    }

    private String getFilterQuery(String[] filters) {


        if (filters != null)
            return " WHERE " + String.join(" OR ", parseFilter(filters)) + "\n";
        return " ";
    }

    private String[] parseFilter(String[] arr) {


        String[] array = new String[arr.length];

        Date now = Date.valueOf(LocalDate.now());

        for (int i = 0; i < array.length; i++) {
            if (arr[i].equals("future")) {
                array[i] = "start_date > '" + now + "'";
            } else if (arr[i].equals("current")) {
                array[i] = "start_date <= '" + now + "' AND '" + now + "' <=  end_date";
            } else if (arr[i].equals("previous")) {
                array[i] = "end_date < '" + now + "'";
            } else
                array[i] = "1=1";

        }

        return array;
    }

    private int getQueryRowCount(String query){
        try (Connection con = DBManager.getInstance().getConnection();
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet scrollableRS = statement.executeQuery(query)) {
            scrollableRS.last();
            return scrollableRS.getRow();
        } catch (SQLException e) {
            logger.error("{}, when trying to get Recruitments rows", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
