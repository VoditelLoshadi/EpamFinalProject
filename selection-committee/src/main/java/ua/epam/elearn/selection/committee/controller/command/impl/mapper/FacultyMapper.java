package ua.epam.elearn.selection.committee.controller.command.impl.mapper;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.validator.FieldValidator;
import ua.epam.elearn.selection.committee.model.dto.FacultyDto;
import ua.epam.elearn.selection.committee.model.dto.UserDto;
import ua.epam.elearn.selection.committee.model.entity.Faculty;


import java.util.HashMap;
import java.util.Map;

public class FacultyMapper {
    private static final String FACULTY_NAME = "facultyName";
    private static final String FACULTY_ID = "facultyId";
    private static final String GENERAL_CAPACITY = "generalCapacity";
    private static final String BUDGET_CAPACITY = "budgetCapacity";
    private static final String SUBJECTS_ID = "subjectId";

    private static final String MIN_PRICE = "minPrice";
    private static final String MAX_PRICE = "maxPrice";
    private static final String PERSON_NUMBER = "personNumber";

    public FacultyDto fetchFacultyDtoFromRequest(HttpServletRequest req) {
        return new FacultyDto(
                req.getParameter(FACULTY_NAME),
                req.getParameter(GENERAL_CAPACITY),
                req.getParameter(BUDGET_CAPACITY)
        );
    }

    public FacultyDto fetchFacultyDtoWithSubjectsFromRequest(HttpServletRequest req) {
        return new FacultyDto(
                req.getParameter(FACULTY_NAME),
                req.getParameter(GENERAL_CAPACITY),
                req.getParameter(BUDGET_CAPACITY),
                req.getParameterValues(SUBJECTS_ID)
        );
    }

    public void insertFacultyDtoIntoRequest(FacultyDto facultyDto, HttpServletRequest req) {
        req.setAttribute(FACULTY_ID, facultyDto.getId());
        req.setAttribute(FACULTY_NAME, facultyDto.getName());
        req.setAttribute(GENERAL_CAPACITY, facultyDto.getGeneralCapacity());
        req.setAttribute(BUDGET_CAPACITY, facultyDto.getBudgetCapacity());

    }

    public void insertFacultyIntoRequest(Faculty faculty, HttpServletRequest req) {
        req.setAttribute(FACULTY_ID, faculty.getId());
        req.setAttribute(FACULTY_NAME, faculty.getName());
        req.setAttribute(GENERAL_CAPACITY, faculty.getGeneralCapacity());
        req.setAttribute(BUDGET_CAPACITY, faculty.getBudgetCapacity());
    }
}
