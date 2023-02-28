package ua.epam.elearn.selection.committee.controller.validator;


public class FilterValidator {
    public static final String FIRST_NAME = "first_name";
    public static final String SECOND_NAME = "second_name";
    public static final String PATRONYMIC = "patronymic";
    public static final String EMAIL = "email";
    public static final String NAME_A = "name_a";
    public static final String NAME_Z = "name_z";
    public static final String GENERAL_CAPACITY = "general_capacity";
    public static final String BUDGET_CAPACITY = "budget_capacity";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";
    public static final String FACULTY_NAME = "faculty.name";
    public static final String RECRUITMENT_NAME = "recruitment.name";


    public static boolean validateSortFilterForUsers(String sort) {
        return sort != null && (sort.equals(FIRST_NAME) || sort.equals(SECOND_NAME)
                || sort.equals(PATRONYMIC) || sort.equals(EMAIL));
    }

    public static boolean validateSortFilterForFaculties(String sort) {
        return sort != null && (sort.equals(NAME_A) || sort.equals(NAME_Z) || sort.equals(GENERAL_CAPACITY) || sort.equals(BUDGET_CAPACITY));
    }

    public static boolean validateSortFilterForRecruitments(String sort) {
        return sort != null && (sort.equals(RECRUITMENT_NAME) || sort.equals(START_DATE) || sort.equals(END_DATE) || sort.equals(FACULTY_NAME));
    }
}
