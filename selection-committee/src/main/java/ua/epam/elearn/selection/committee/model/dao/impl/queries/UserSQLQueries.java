package ua.epam.elearn.selection.committee.model.dao.impl.queries;

public class UserSQLQueries {


    private UserSQLQueries() {
    }


    public static final String CREATE_USER = "INSERT INTO \"user\" (login, email, password, first_name, second_name, patronymic, city, region, institution, role_id)" +
            " values (?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";

    public static final String SELECT_USER_BY_ID = "SELECT * FROM \"user\" WHERE id= ?";

    public static final String SELECT_ROLE_BY_ROLE_ID = "SELECT name FROM roles WHERE id=?";

    public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM \"user\" WHERE email= ?";

    public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM \"user\" WHERE login= ?";

    public static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM \"user\" WHERE login = ? AND password = ?";

    public static final String SELECT_ALL_USERS = "SELECT * FROM \"user\"\n";

    public static final String BLOCK_USER_BY_ID = "UPDATE \"user\" SET blocked=true WHERE id = ?";

    public static final String UNBLOCK_USER_BY_ID = "UPDATE \"user\" SET blocked=false WHERE id = ?";

    public static final String SET_USER_ROLE_BY_ID = "UPDATE \"user\" SET role_id = ? WHERE id = ?";
}
