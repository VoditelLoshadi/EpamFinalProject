package ua.epam.elearn.selection.committee.controller.validator;

public class UserFieldLimit {
    private UserFieldLimit() {
    }

    public static final int LOGIN_MIN_SIZE = 4;
    public static final int LOGIN_MAX_SIZE = 50 ;

    public static final int EMAIL_MIN_SIZE = 4;
    public static final int EMAIL_MAX_SIZE = 50 ;

    public static final int PASSWORD_MIN_SIZE = 8;
    public static final int PASSWORD_MAX_SIZE = 64;

    public static final int FIRST_NAME_MAX_SIZE = 32 ;
    public static final int SECOND_NAME_MAX_SIZE = 32 ;
    public static final int PATRONYMIC_MAX_SIZE = 32 ;

    public static final int CITY_MAX_SIZE = 32;
    public static final int REGION_MAX_SIZE = 32 ;
    public static final int INSTITUTION_MAX_SIZE = 64 ;
}
