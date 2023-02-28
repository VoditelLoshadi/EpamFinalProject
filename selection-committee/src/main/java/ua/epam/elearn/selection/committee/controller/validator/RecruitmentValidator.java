package ua.epam.elearn.selection.committee.controller.validator;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.exception.EmptyFieldException;
import ua.epam.elearn.selection.committee.controller.exception.recruitment.DateNotMatchTemplateException;
import ua.epam.elearn.selection.committee.controller.exception.recruitment.DateOutOfDateException;
import ua.epam.elearn.selection.committee.controller.exception.recruitment.EndDateBeforeStartDateException;
import ua.epam.elearn.selection.committee.controller.validator.regexp.RegExp;
import ua.epam.elearn.selection.committee.model.dto.RecruitmentDto;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecruitmentValidator {

    public static final String EMPTY_FIELD = "emptyFieldException";
    public static final String DATE_NOT_MATCH_TEMPLATE = "dateNotMatchTemplateException";
    public static final String DATE_OUT_OF_DATE = "dateOutOfDateException";
    public static final String END_DATE_BEFORE_START_DATE = "endDateBeforeStartDateException";

    public static boolean validate(RecruitmentDto recruitmentDto, HttpServletRequest request) {
        try {
            checkEmptyField(recruitmentDto.getName());
            checkDateMatchingTemplate(recruitmentDto.getStartDate());
            checkDateValid(recruitmentDto.getStartDate());
            checkDateMatchingTemplate(recruitmentDto.getEndDate());
            checkStartEndDime(recruitmentDto.getStartDate(), recruitmentDto.getEndDate());
            return true;
        } catch (EmptyFieldException e) {
            request.setAttribute(EMPTY_FIELD, true);
        } catch (DateNotMatchTemplateException e) {
            request.setAttribute(DATE_NOT_MATCH_TEMPLATE, true);
        } catch (DateOutOfDateException e) {
            request.setAttribute(DATE_OUT_OF_DATE, true);
        } catch (EndDateBeforeStartDateException e) {
            request.setAttribute(END_DATE_BEFORE_START_DATE, true);
        }

        return false;
    }

    private static void checkEmptyField(String field) throws EmptyFieldException {
        if (FieldValidator.fieldIsEmpty(field))
            throw new EmptyFieldException();

    }

    private static void checkDateMatchingTemplate(String date) throws DateNotMatchTemplateException {
        Pattern pattern = Pattern.compile(RegExp.DATE);
        Matcher matcher = pattern.matcher(date);

        if (!matcher.matches()) {
            throw new DateNotMatchTemplateException();
        }
    }


    private static void checkDateValid(String date) throws DateOutOfDateException {
        if (LocalDate.parse(date).isBefore(LocalDate.now()))
            throw new DateOutOfDateException();

    }

    private static void checkStartEndDime(String startDate, String endDate) throws EndDateBeforeStartDateException {
        if (LocalDate.parse(startDate).isAfter(LocalDate.parse(endDate)))
            throw new EndDateBeforeStartDateException();
    }
}
