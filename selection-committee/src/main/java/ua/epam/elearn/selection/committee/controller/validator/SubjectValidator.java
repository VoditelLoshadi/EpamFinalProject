package ua.epam.elearn.selection.committee.controller.validator;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.exception.EmptyFieldException;
import ua.epam.elearn.selection.committee.model.dto.SubjectDto;



public class SubjectValidator {

    public static final String EMPTY_FIELD_EXCEPTION = "emptyFieldException";

    private SubjectValidator() {
    }

    public static boolean validate(SubjectDto subjectDto, HttpServletRequest request) {
        try {
            checkEmptyField(subjectDto.getNameEn());
            checkEmptyField(subjectDto.getNameRu());
            checkEmptyField(subjectDto.getNameUk());
            return true;
        } catch (EmptyFieldException e) {

            request.setAttribute(EMPTY_FIELD_EXCEPTION, true);
        }
        return false;
    }


    private static void checkEmptyField(String field) throws EmptyFieldException {
        if (field == null || field.trim().isEmpty()) {
            throw new EmptyFieldException();
        }
    }
}
