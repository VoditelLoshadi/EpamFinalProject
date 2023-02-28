package ua.epam.elearn.selection.committee.controller.command.impl.common;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.path.JspFilePath;
import ua.epam.elearn.selection.committee.controller.validator.FieldValidator;
import ua.epam.elearn.selection.committee.controller.validator.FilterValidator;
import ua.epam.elearn.selection.committee.model.entity.Faculty;
import ua.epam.elearn.selection.committee.model.entity.Subject;
import ua.epam.elearn.selection.committee.model.services.FacultyService;
import ua.epam.elearn.selection.committee.model.services.SubjectService;


import java.util.List;

public class GetFacultiesCommand implements Command {

    private static final Integer START_PAGE_NUMBER = 1;
    private static final String FACULTY_LIST = "facultyList";
    private static final String SUBJECT_LIST = "subjectList";
    private static final String PAGES_NUMBER = "pagesNumber";
    private static final String CURRENT_PAGE_NUMBER = "currentPageNumber";
    private static final String PAGE = "page";
    private static final String SORT = "sort";
    private static final String ID = "id";

    FacultyService facultyService;
    SubjectService subjectService;

    public GetFacultiesCommand(FacultyService facultyService, SubjectService subjectService) {
        this.facultyService = facultyService;
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        List<Subject> subjectList = subjectService.getAllSubjects();

        request.setAttribute(SUBJECT_LIST, subjectList);

        int activePageNumber = getActivePageNumber(request);
        String orderBy = getSortFilter(request);

        int pagesNumber = facultyService.getCountOfFaculties();
        List<Faculty> facultyList = facultyService.getPaginationAllFaculties(orderBy, activePageNumber);

        request.setAttribute(FACULTY_LIST, facultyList);
        request.setAttribute(PAGES_NUMBER, pagesNumber);
        request.setAttribute(CURRENT_PAGE_NUMBER, activePageNumber);

        return JspFilePath.FACULTIES;
    }

    private int getActivePageNumber(HttpServletRequest request) {
        String pageNumberString = request.getParameter(PAGE);

        if (!FieldValidator.fieldIsNotValidInt(pageNumberString)) {
            return Integer.parseInt(pageNumberString);
        } else {
            return START_PAGE_NUMBER;
        }
    }

    private String getSortFilter(HttpServletRequest request) {
        String sortFilter = request.getParameter(SORT);

        if (FilterValidator.validateSortFilterForFaculties(sortFilter)) {

            return sortFilter;
        } else {
            return ID;
        }
    }

}
