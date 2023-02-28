package ua.epam.elearn.selection.committee.controller.command.impl.mapper;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.validator.FieldValidator;
import ua.epam.elearn.selection.committee.model.dto.ApplicationDto;
import ua.epam.elearn.selection.committee.model.dto.FacultyDto;
import ua.epam.elearn.selection.committee.model.dto.RecruitmentDto;


import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RecruitmentMapper {

    private static final String RECRUITMENT_NAME = "name";
    private static final String FACULTY_ID = "facultyId";
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String SUBJECTS_ID = "subjectId";

    private static final String PREVIOUS = "previous";
    private static final String CURRENT = "current";
    private static final String FUTURE = "future";
    private static final String PAGE = "page";
    private static final String FILTER = "filter";


    public RecruitmentDto fetchRecruitmentDtoFromRequest(HttpServletRequest req) {
        return new RecruitmentDto(
                req.getParameter(RECRUITMENT_NAME),
                req.getParameter(FACULTY_ID),
                req.getParameter(START_DATE),
                req.getParameter(END_DATE)
        );
    }

    public void insertRecruitmentDtoIntoRequest(RecruitmentDto recruitmentDto, HttpServletRequest req) {
        req.setAttribute(RECRUITMENT_NAME, recruitmentDto.getName());
        req.setAttribute(FACULTY_ID, recruitmentDto.getFacultyId());
        req.setAttribute(START_DATE, recruitmentDto.getStartDate());
        req.setAttribute(END_DATE, recruitmentDto.getEndDate());
    }

    public String[] fetchFiltersFromRequest(HttpServletRequest req) {
        String arr = req.getParameter(FILTER);

        if (arr != null) {
            return arr.split(",");
        }
        return new String[]{"current"};


    }

    public void insertFiltersIntoRequest(String[] filters, HttpServletRequest req) {

        if (filters != null)
            for (String s : filters) {
                req.setAttribute(s, true);
            }

    }
}
