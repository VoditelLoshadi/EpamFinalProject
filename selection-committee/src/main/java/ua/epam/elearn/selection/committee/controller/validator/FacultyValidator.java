package ua.epam.elearn.selection.committee.controller.validator;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.exception.faculty.CapacityIncorrectException;
import ua.epam.elearn.selection.committee.controller.exception.faculty.EmptyNameFieldException;
import ua.epam.elearn.selection.committee.controller.exception.faculty.GeneralCapacityIncorrectException;
import ua.epam.elearn.selection.committee.controller.exception.faculty.GeneralCapacityLessBudgetCapacityException;
import ua.epam.elearn.selection.committee.controller.validator.exceptions.AdminExceptions;
import ua.epam.elearn.selection.committee.model.dto.FacultyDto;
import ua.epam.elearn.selection.committee.controller.exception.faculty.FewRequiredSubjectsException;


public class FacultyValidator {
    private FacultyValidator() {
    }

    public static boolean validate(FacultyDto facultyDto, HttpServletRequest request) {
        try {
            checkNameFieldCorrect(facultyDto.getName());
            checkGeneralCapacityCorrect(facultyDto.getGeneralCapacity());
            checkBudgetCapacityCorrect(facultyDto.getBudgetCapacity());
            checkCapacityCorrect(facultyDto.getGeneralCapacity(), facultyDto.getBudgetCapacity());
            checkSubjectListCorrect(facultyDto.getRequiredSubjectIdList());
            return true;
        } catch (CapacityIncorrectException e) {
            request.setAttribute(AdminExceptions.CAPACITY_INCORRECT_EXCEPTION, true);
        } catch (GeneralCapacityIncorrectException e) {
            request.setAttribute(AdminExceptions.GENERAL_CAPACITY_INCORRECT_EXCEPTION, true);
        } catch (GeneralCapacityLessBudgetCapacityException e) {
            request.setAttribute(AdminExceptions.GENERAL_CAPACITY_LESS_BUDGET_CAPACITY_EXCEPTION, true);
        } catch (EmptyNameFieldException e) {
            request.setAttribute(AdminExceptions.EMPTY_NAME_FIELD_EXCEPTION, true);
        } catch (FewRequiredSubjectsException e) {
            request.setAttribute(AdminExceptions.FEW_REQUIRED_SUBJECTS_EXCEPTION, true);
        }
        return false;
    }

    private static void checkGeneralCapacityCorrect(String generalCapacity) throws GeneralCapacityIncorrectException {
        if (FieldValidator.fieldIsEmpty(generalCapacity) || FieldValidator.fieldIsNotValidLong(generalCapacity)) {
            throw new GeneralCapacityIncorrectException();
        }

        long generalCapacityLong = Long.parseLong(generalCapacity);
        if (generalCapacityLong < 1) {
            throw new GeneralCapacityIncorrectException();
        }
    }

    private static void checkBudgetCapacityCorrect(String budgetCapacity) throws CapacityIncorrectException {
        if (FieldValidator.fieldIsEmpty(budgetCapacity) || FieldValidator.fieldIsNotValidLong(budgetCapacity)) {
            throw new CapacityIncorrectException();
        }

        long budgetCapacityLong = Long.parseLong(budgetCapacity);
        if (budgetCapacityLong < 0) {
            throw new CapacityIncorrectException();
        }
    }

    private static void checkCapacityCorrect(String generalCapacity, String budgetCapacity) throws CapacityIncorrectException, GeneralCapacityIncorrectException, GeneralCapacityLessBudgetCapacityException {

        checkGeneralCapacityCorrect(generalCapacity);
        checkBudgetCapacityCorrect(budgetCapacity);

        long generalCapacityLong = Long.parseLong(generalCapacity);
        long budgetCapacityLong = Long.parseLong(budgetCapacity);
        if (generalCapacityLong < budgetCapacityLong)
            throw new GeneralCapacityLessBudgetCapacityException();

    }

    private static void checkNameFieldCorrect(String field) throws EmptyNameFieldException {
        if (FieldValidator.fieldIsEmpty(field)) {
            throw new EmptyNameFieldException();
        }
    }

    private static void checkSubjectListCorrect(String[] subjectIdArray) throws FewRequiredSubjectsException {
        if (subjectIdArray == null || subjectIdArray.length < 3)
            throw new FewRequiredSubjectsException();

        for (String i : subjectIdArray) {
            if ( FieldValidator.fieldIsNotValidLong(i)) {
                throw new FewRequiredSubjectsException();
            }
        }
    }

}
