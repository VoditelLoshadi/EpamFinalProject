package ua.epam.elearn.selection.committee.controller.validator;

public class FieldValidator {
    private FieldValidator() {}

    public static boolean fieldIsEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }

    public static boolean fieldIsNotValidLong(String field) {
        try {
            Long.parseLong(field);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    public static boolean fieldIsNotValidInt(String field) {
        try {
            Long.parseLong(field);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }
}
