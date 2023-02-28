package ua.epam.elearn.selection.committee.controller.validator;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.exception.application.InvalidGradeException;
import ua.epam.elearn.selection.committee.controller.exception.application.InvalidNumberException;
import ua.epam.elearn.selection.committee.controller.exception.faculty.CapacityIncorrectException;
import ua.epam.elearn.selection.committee.controller.exception.user.LoginSizeOutOfBoundsException;
import ua.epam.elearn.selection.committee.controller.exception.user.PasswordsNotSameException;
import ua.epam.elearn.selection.committee.controller.validator.exceptions.UserExceptions;
import ua.epam.elearn.selection.committee.model.dto.ApplicationDto;



public class ApplicationValidator {

    public static final String INVALID_NUMBER_EXCEPTION = "InvalidNumberException";
    public static final String INVALID_GRADE_EXCEPTION = "InvalidGradeException";

    public static boolean validate(ApplicationDto applicationDto, HttpServletRequest request) {
        try {
            checkFieldCorrect(applicationDto.getGrades());
            return true;
        } catch (InvalidNumberException e) {
            request.setAttribute(INVALID_NUMBER_EXCEPTION, true);
        } catch (InvalidGradeException e) {
            request.setAttribute(INVALID_GRADE_EXCEPTION, true);
        }
        return false;
    }

    private static void checkFieldCorrect(String[] grades) throws InvalidNumberException, InvalidGradeException {

        for (String grade : grades) {
            if (FieldValidator.fieldIsEmpty(grade) || FieldValidator.fieldIsNotValidLong(grade)) {
                throw new InvalidNumberException();
            }
            if (isValidGrade(Long.parseLong(grade)))
                throw new InvalidGradeException();
        }

    }

    private static boolean isValidGrade(long grade) {
        return grade < 100 || grade > 200;
    }
}
