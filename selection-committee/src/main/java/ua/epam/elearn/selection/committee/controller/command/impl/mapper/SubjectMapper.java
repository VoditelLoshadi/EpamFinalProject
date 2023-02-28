package ua.epam.elearn.selection.committee.controller.command.impl.mapper;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.model.dto.FacultyDto;
import ua.epam.elearn.selection.committee.model.dto.SubjectDto;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SubjectMapper {

    private static final String SUBJECT_NAME_RU = "nameRu";
    private static final String SUBJECT_NAME_EN = "nameEn";
    private static final String SUBJECT_NAME_UK = "nameUk";
    private static final String SUBJECT_ID = "subjectId";

    public SubjectDto fetchSubjectDtoFromRequest(HttpServletRequest req) {
        return new SubjectDto(
                req.getParameter(SUBJECT_NAME_EN),
                req.getParameter(SUBJECT_NAME_RU),
                req.getParameter(SUBJECT_NAME_UK)
        );
    }

    public List<Long> fetchSubjectListDtoFromRequest(HttpServletRequest req) {
        return Arrays.stream(req.getParameterValues(SUBJECT_ID)).map(Long::parseLong).collect(Collectors.toList());
    }
}
