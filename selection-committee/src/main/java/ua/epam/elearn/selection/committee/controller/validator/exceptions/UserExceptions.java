package ua.epam.elearn.selection.committee.controller.validator.exceptions;

public class UserExceptions {
    private UserExceptions() {
    }

    public static final String LOGIN_SIZE_OUT_OF_BOUNDS = "loginSizeOutOfBoundsException";
    public static final String EMAIL_SIZE_OUT_OF_BOUNDS = "emailSizeOutOfBoundsException";
    public static final String FIRST_NAME_SIZE_OUT_OF_BOUNDS = "firstNameSizeOutOfBoundsException";
    public static final String SECOND_NAME_SIZE_OUT_OF_BOUNDS = "secondNameSizeOutOfBoundsException";
    public static final String PATRONYMIC_SIZE_OUT_OF_BOUNDS = "patronymicSizeOutOfBoundsException";
    public static final String PASSWORD_SIZE_OUT_OF_BOUNDS = "passwordSizeOutOfBoundsException";
    public static final String CITY_SIZE_OUT_OF_BOUNDS = "citySizeOutOfBoundsException";
    public static final String REGION_SIZE_OUT_OF_BOUNDS = "regionSizeOutOfBoundsException";
    public static final String INSTITUTION_SIZE_OUT_OF_BOUNDS = "institutionSizeOutOfBoundsException";

    public static final String EMAIL_NOT_MATCH_TEMPLATE = "emailNotMatchTemplateException";
    public static final String PASSWORD_NOT_MATCH_TEMPLATE = "passwordNotMatchTemplateException";
    public static final String PASSWORDS_NOT_SAME = "passwordsNotSameException";
}
