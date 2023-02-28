package ua.epam.elearn.selection.committee.model.dao.impl;


import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.elearn.selection.committee.model.dao.FacultyDao;
import ua.epam.elearn.selection.committee.model.dao.database.DBManager;
import ua.epam.elearn.selection.committee.model.dao.impl.queries.FacultySQLQueries;
import ua.epam.elearn.selection.committee.model.dao.mapper.FacultyMapper;
import ua.epam.elearn.selection.committee.model.entity.Faculty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultyDaoImpl implements FacultyDao {

    private final Logger logger = LoggerFactory.getLogger(FacultyDaoImpl.class);


    @Override
    public boolean update(Faculty faculty) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(FacultySQLQueries.UPDATE)) {

            stmt.setString(1, faculty.getName());
            stmt.setLong(2, faculty.getGeneralCapacity());
            stmt.setLong(3, faculty.getBudgetCapacity());
            stmt.setLong(4, faculty.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getAllFacultiesSize() {
        return getQueryRowCount(FacultySQLQueries.SELECT_ALL_FACULTIES);
    }

    @Override
    public Faculty getFacultyById(long id) {
        return getFacultyByIdAndQuery(id, FacultySQLQueries.SELECT_FACULTY_BY_ID);
    }

    @Override
    public Faculty getFacultyByRecruitmentId(long recruitmentId) {
        return getFacultyByIdAndQuery(recruitmentId, FacultySQLQueries.SELECT_FACULTY_BY_RECRUITMENT_ID);
    }

    private Faculty getFacultyByIdAndQuery(long id, String query) {
        Faculty faculty = null;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                FacultyMapper facultyMapper = new FacultyMapper();

                while (rs.next()) {
                    faculty = facultyMapper.extractFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return faculty;
    }

    @Override
    public Faculty getFacultyByName(String name) {
        Faculty faculty = null;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(FacultySQLQueries.SELECT_FACULTY_BY_NAME)) {
            stmt.setString(1, name);

            try (ResultSet rs = stmt.executeQuery()) {
                FacultyMapper facultyMapper = new FacultyMapper();

                while (rs.next()) {
                    faculty = facultyMapper.extractFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return faculty;
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return getAllFacultiesByQuery(FacultySQLQueries.SELECT_ALL_FACULTIES);
    }

    @Override
    public List<Faculty> getPaginationAllFaculties(String order, int limit, int offset) {
        String query = FacultySQLQueries.SELECT_ALL_FACULTIES +
                getOrderByQuery(order) +
                getPaginationPageQuery(limit, offset);

        return getAllFacultiesByQuery(query);
    }

    @Override
    public List<Faculty> getAllFacultiesWhereOpenedRecruitments() {
        return getAllFacultiesByQuery(FacultySQLQueries.SELECT_ALL_FACULTIES_WHERE_OPENED_RECRUITMENT);
    }

    private List<Faculty> getAllFacultiesByQuery(String query) {

        List<Faculty> faculties = new ArrayList<>();

        try (Connection con = DBManager.getInstance().getConnection();
             Statement stmt = con.createStatement()) {

            try (ResultSet rs = stmt.executeQuery(query)) {
                FacultyMapper facultyMapper = new FacultyMapper();
                while (rs.next()) {
                    faculties.add(facultyMapper.extractFromResultSet(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return faculties;
    }

    @Override
    public boolean addFaculty(Faculty faculty) {

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(FacultySQLQueries.CREATE_FACULTY)) {

            stmt.setString(1, faculty.getName());
            stmt.setLong(2, faculty.getGeneralCapacity());
            stmt.setLong(3, faculty.getBudgetCapacity());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isExistedByFacultyId(Long facultyId) {
        return getFacultyById(facultyId) != null;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(FacultySQLQueries.DELETE)) {

            stmt.setLong(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            logger.error("{}, when trying to delete Faculty by ID = {}", e.getMessage(), id);
            throw new RuntimeException(e);
        }
    }

    private String getPaginationPageQuery(int limit, int offset) {
        return "LIMIT " + limit + " OFFSET " + offset + "\n";
    }

    private String getOrderByQuery(String order) {
        return "ORDER BY " + parseOrder(order) + "\n";
    }

    private String parseOrder(String order) {
        if (order.equals("name_a"))
            return "name ASC";
        if (order.equals("name_z"))
            return "name DESC";
        return order;
    }

    private int getQueryRowCount(String query){
        try (Connection con = DBManager.getInstance().getConnection();
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet scrollableRS = statement.executeQuery(query)) {
            scrollableRS.last();
            return scrollableRS.getRow();
        } catch (SQLException e) {
            logger.error("{}, when trying to get Faculties rows", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
