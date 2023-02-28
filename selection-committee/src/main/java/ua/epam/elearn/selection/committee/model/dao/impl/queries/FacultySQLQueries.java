package ua.epam.elearn.selection.committee.model.dao.impl.queries;

public class FacultySQLQueries {
    private FacultySQLQueries() {
    }

    public static final String UPDATE =
            "UPDATE faculty\n" +
                    "SET name         = ?,\n" +
                    "general_capacity = ?,\n" +
                    "budget_capacity  = ? \n" +
                    "WHERE id = ?";

    public static final String CREATE_FACULTY =
            "INSERT INTO faculty (name, general_capacity, budget_capacity) values (?, ?, ?)";

    public static final String SELECT_FACULTY_BY_ID =
            "SELECT * FROM faculty WHERE id= ?";

    public static final String SELECT_FACULTY_BY_RECRUITMENT_ID =
            "SELECT faculty.* FROM faculty\n" +
            "JOIN recruitment ON faculty.id = recruitment.faculty_id\n" +
            "WHERE recruitment.id = ?";

    public static final String SELECT_FACULTY_BY_NAME =
            "SELECT * FROM faculty WHERE name= ?";

    public static final String SELECT_ALL_FACULTIES =
            "SELECT * FROM faculty\n";

    public static final String SELECT_ALL_FACULTIES_WHERE_OPENED_RECRUITMENT =
            "SELECT faculty.* FROM faculty " +
            "JOIN recruitment ON faculty.id = recruitment.faculty_id " +
            "WHERE recruitment.closed = false";

    public static final String DELETE =
            "DELETE FROM faculty WHERE id = ?";

}
