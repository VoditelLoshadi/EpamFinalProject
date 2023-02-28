package ua.epam.elearn.selection.committee.controller.command.impl.admin;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.path.JspFilePath;
import ua.epam.elearn.selection.committee.controller.validator.FieldValidator;
import ua.epam.elearn.selection.committee.controller.validator.FilterValidator;
import ua.epam.elearn.selection.committee.model.entity.User;
import ua.epam.elearn.selection.committee.model.services.UserService;


import java.util.List;

public class GetUsersCommand implements Command {
    private static final Integer START_PAGE_NUMBER = 1;
    private static final String CURRENT_PAGE_NUMBER = "currentPageNumber";
    private static final String PAGES_NUMBER = "pagesNumber";
    private static final String PAGE = "page";
    private static final String ID = "id";
    private static final String SORT = "sort";
    private static final String USERS = "users";

    private UserService userService;

    public GetUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        int activePageNumber = getActivePageNumber(request);
        String orderBy = getSortFilter(request);
        int pagesNumber = userService.getCountPagesOfUsers();

        List<User> userList = userService.getPaginationAllUsers(orderBy, activePageNumber);

        request.setAttribute(USERS, userList);
        request.setAttribute(PAGES_NUMBER, pagesNumber);
        request.setAttribute(CURRENT_PAGE_NUMBER, activePageNumber);

        return JspFilePath.USERS;
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

        if (FilterValidator.validateSortFilterForUsers(sortFilter)) {
            return sortFilter;
        } else {
            return ID;
        }
    }
}
