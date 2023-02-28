package ua.epam.elearn.selection.committee.model.dao.impl;


import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.elearn.selection.committee.model.dao.database.DBManager;
import ua.epam.elearn.selection.committee.model.dao.UserDao;
import ua.epam.elearn.selection.committee.model.dao.impl.queries.FacultySQLQueries;
import ua.epam.elearn.selection.committee.model.dao.impl.queries.UserSQLQueries;
import ua.epam.elearn.selection.committee.model.dao.mapper.UserMapper;
import ua.epam.elearn.selection.committee.model.entity.Faculty;
import ua.epam.elearn.selection.committee.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final int USER = 1;
    private static final int ADMIN = 2;

    private final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public boolean createUser(User user) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(UserSQLQueries.CREATE_USER)) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getSecondName());
            stmt.setString(6, user.getPatronymic());
            stmt.setString(7, user.getCity());
            stmt.setString(8, user.getRegion());
            stmt.setString(9, user.getInstitution());
            stmt.setInt(10, USER);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            logger.error("{}, when trying to create User = {}", e.getMessage(), user);
            throw new RuntimeException(e);
        } finally {

        }
    }

    @Override
    public User getUserById(long id) {
        User user = null;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(UserSQLQueries.SELECT_USER_BY_ID)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                UserMapper userMapper = new UserMapper();

                while (rs.next()) {
                    user = userMapper.extractFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            logger.error("{}, when trying to get User by Id = {}", e.getMessage(), id);
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return getUserByParam(email, UserSQLQueries.SELECT_USER_BY_EMAIL);
    }

    @Override
    public User getUserByLogin(String login) {
        return getUserByParam(login, UserSQLQueries.SELECT_USER_BY_LOGIN);
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        User user = null;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(UserSQLQueries.SELECT_USER_BY_LOGIN_AND_PASSWORD)) {
            stmt.setString(1, login);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                UserMapper userMapper = new UserMapper();

                while (rs.next()) {
                    user = userMapper.extractFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            logger.warn("User with (login = {} and password = {}) doesn't exist", login, password);
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {

        return getAllUsersByQuery(UserSQLQueries.SELECT_ALL_USERS);
    }

    @Override
    public List<User> getPaginationAllUsers(String order, int limit, int offset) {
        String query = UserSQLQueries.SELECT_ALL_USERS +
                getOrderByQuery(order) +
                getPaginationPageQuery(limit, offset);

        return getAllUsersByQuery(query);
    }

    private List<User> getAllUsersByQuery(String query){
        List<User> userList = new ArrayList<>();
        try (Connection con = DBManager.getInstance().getConnection();
             Statement stmt = con.createStatement()) {


            try (ResultSet rs = stmt.executeQuery(query)) {
                UserMapper userMapper = new UserMapper();
                while (rs.next()) {
                    userList.add(userMapper.extractFromResultSet(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    @Override
    public String getRoleByRoleId(long roleId) {
        String role = null;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(UserSQLQueries.SELECT_ROLE_BY_ROLE_ID)) {

            stmt.setLong(1, roleId);

            try (ResultSet rs = stmt.executeQuery()) {


                while (rs.next()) {
                    role = rs.getString(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return role;
    }

    @Override
    public boolean blockUserById(long id) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(UserSQLQueries.BLOCK_USER_BY_ID)) {

            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean unblockUserById(long id) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(UserSQLQueries.UNBLOCK_USER_BY_ID)) {

            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getAllUsersSize() {
        return getQueryRowCount(UserSQLQueries.SELECT_ALL_USERS);
    }


    private User getUserByParam(String param, String query) {
        User user = null;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, param);

            try (ResultSet rs = stmt.executeQuery()) {
                UserMapper userMapper = new UserMapper();

                while (rs.next()) {
                    user = userMapper.extractFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    private String getPaginationPageQuery(int limit, int offset) {
        return "LIMIT " + limit + " OFFSET " + offset + "\n";
    }

    private String getOrderByQuery(String order) {
        return "ORDER BY " + order + "\n";
    }

    private int getQueryRowCount(String query) {
        try (Connection con = DBManager.getInstance().getConnection();
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet scrollableRS = statement.executeQuery(query)) {
            scrollableRS.last();
            return scrollableRS.getRow();
        } catch (SQLException e) {
            logger.error("{}, when trying to get Users rows", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
