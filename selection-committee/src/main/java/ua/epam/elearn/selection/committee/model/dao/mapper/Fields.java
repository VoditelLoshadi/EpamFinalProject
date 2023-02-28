package ua.epam.elearn.selection.committee.model.dao.mapper;

public class Fields {
    private Fields() {
    }


    // ----- Universal --------

    public static String ID = "id";

    public static String NAME = "name";

    // ----- User --------

    public static String LOGIN = "login";

    public static String EMAIL = "email";

    public static String PASSWORD = "password";

    public static String FIRST_NAME = "first_name";

    public static String SECOND_NAME = "second_name";

    public static String PATRONYMIC = "patronymic";

    public static String CITY = "city";

    public static String REGION = "region";

    public static String INSTITUTION = "institution";

    public static String IS_BLOCKED = "blocked";

    public static String ROLE_ID = "role_id";


    // ----- Faculty --------

    public static String GENERAL_CAPACITY = "general_capacity";

    public static String BUDGET_CAPACITY = "budget_capacity";

    // ----- Subject --------

    public static String NAME_EN = "name_en";

    public static String NAME_RU = "name_ru";

    public static String NAME_UK = "name_uk";


    // ----- Recruitment --------

    public static String RECRUITMENT_NAME = "name";

    public static String START_DATE = "start_date";

    public static String END_DATE = "end_date";

    public static String CLOSED = "closed";

    public static String FACULTY_ID = "faculty_id";


    // ----- Application --------

    public static String USER_ID = "user_id";

    public static String APPLICATION_STATE_ID = "application_state_id";

    public static String RECRUITMENT_ID = "recruitment_id";

    public static String GRADES = "grades";


    // ----- Application state --------

    public static String STATE = "state";

    // ----- Grade --------

    public static String GRADE = "grade";

    public static String SUBJECT_ID = "subject_id";

    public static String APPLICATION_ID = "application_id";
}
