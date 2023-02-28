package ua.epam.elearn.selection.committee.controller.command.impl.common;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.command.impl.mapper.RecruitmentMapper;
import ua.epam.elearn.selection.committee.controller.path.JspFilePath;
import ua.epam.elearn.selection.committee.controller.validator.FieldValidator;
import ua.epam.elearn.selection.committee.controller.validator.FilterValidator;
import ua.epam.elearn.selection.committee.model.entity.Faculty;
import ua.epam.elearn.selection.committee.model.entity.Recruitment;
import ua.epam.elearn.selection.committee.model.entity.User;
import ua.epam.elearn.selection.committee.model.services.FacultyService;
import ua.epam.elearn.selection.committee.model.services.RecruitmentService;

import java.time.LocalDate;
import java.util.*;

public class GetRecruitmentsCommand implements Command {

    private static final Integer START_PAGE_NUMBER = 1;
    private static final String PAGE = "page";
    private static final String SORT = "sort";
    private static final String ID = "id";
    private static final String RECRUITMENT_MAP = "recruitmentMap";

    private static final String PAGES_NUMBER = "pagesNumber";
    private static final String CURRENT_PAGE_NUMBER = "currentPageNumber";

    private final RecruitmentMapper recruitmentMapper = new RecruitmentMapper();

    private final FacultyService facultyService;
    private final RecruitmentService recruitmentService;

    public GetRecruitmentsCommand(FacultyService facultyService, RecruitmentService recruitmentService) {
        this.facultyService = facultyService;
        this.recruitmentService = recruitmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String[] filterParameters = recruitmentMapper.fetchFiltersFromRequest(request);


        int activePageNumber = getActivePageNumber(request);
        int pagesNumber = recruitmentService.getCountOfFaculties(filterParameters);

        if (pagesNumber <= activePageNumber)
            activePageNumber = pagesNumber;

        String orderBy = getSortFilter(request);
        User user = (User) request.getSession().getAttribute("user");



        Map<Recruitment, Faculty> recruitmentMap = recruitmentService
                .getPaginationAllRecruitmentsWithFaculties(filterParameters, orderBy, activePageNumber);




        for (Recruitment recruitment : recruitmentMap.keySet()) {

            if (user != null && recruitmentService.getRecruitmentApplicationStatusByUserId(recruitment.getId(), user.getId()) != null)
                recruitment.setClosed(true);
            if (recruitment.getStartDate().isAfter(LocalDate.now()))
                recruitment.setClosed(true);
        }

        request.setAttribute(RECRUITMENT_MAP, recruitmentMap);
        request.setAttribute(PAGES_NUMBER, pagesNumber);
        request.setAttribute(CURRENT_PAGE_NUMBER, activePageNumber);

        recruitmentMapper.insertFiltersIntoRequest(filterParameters, request);

        return JspFilePath.RECRUITMENTS;
    }

    private String getSortFilter(HttpServletRequest request) {
        String sortFilter = request.getParameter(SORT);

        if (FilterValidator.validateSortFilterForRecruitments(sortFilter)) {
            return sortFilter;
        } else {
            return "recruitment." + ID;
        }
    }


    private int getActivePageNumber(HttpServletRequest request) {
        String pageNumberString = request.getParameter(PAGE);

        if (!FieldValidator.fieldIsNotValidInt(pageNumberString)) {
            return Integer.parseInt(pageNumberString);
        } else {
            return START_PAGE_NUMBER;
        }
    }
}
