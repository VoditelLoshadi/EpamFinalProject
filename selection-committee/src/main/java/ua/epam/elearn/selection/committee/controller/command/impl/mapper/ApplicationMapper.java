package ua.epam.elearn.selection.committee.controller.command.impl.mapper;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.validator.FieldValidator;
import ua.epam.elearn.selection.committee.model.dto.ApplicationDto;


import java.util.HashMap;
import java.util.Map;

public class ApplicationMapper {

    private static final String USER_ID = "userId";
    private static final String RECRUITMENT_ID = "recruitmentId";
    private static final String GRADES = "grades";




    public ApplicationDto fetchApplicationDtoFromRequest(HttpServletRequest req) {
        return new ApplicationDto(
                req.getParameter(USER_ID),
                req.getParameter(RECRUITMENT_ID)
        );
    }

    public ApplicationDto fetchApplicationWithGradeDtoFromRequest(HttpServletRequest req) {
        return new ApplicationDto(
                req.getParameter(USER_ID),
                req.getParameter(RECRUITMENT_ID),
                req.getParameterValues(GRADES)
        );
    }

    public void insertApplicationDtoIntoRequest(ApplicationDto applicationDto, HttpServletRequest req) {
        req.setAttribute(USER_ID, applicationDto.getUserId());
        req.setAttribute(RECRUITMENT_ID, applicationDto.getRecruitmentId());
    }



}
