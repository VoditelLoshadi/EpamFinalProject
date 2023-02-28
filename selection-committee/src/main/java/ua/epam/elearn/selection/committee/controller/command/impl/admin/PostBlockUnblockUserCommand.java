package ua.epam.elearn.selection.committee.controller.command.impl.admin;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.path.UrlPath;
import ua.epam.elearn.selection.committee.model.services.UserService;


public class PostBlockUnblockUserCommand implements Command {
    private static final String USER_ID = "userId";
    private static final String USER_BLOCKED = "userBlocked";
    private static final String FALSE = "false";
    private static final String TRUE = "true";

    UserService userService;

    public PostBlockUnblockUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        Long userId = Long.valueOf(request.getParameter(USER_ID));
        String blocked = request.getParameter(USER_BLOCKED);

        if (blocked.equals(FALSE)) {
            userService.blockById(userId);
        } else if (blocked.equals(TRUE)) {
            userService.unblockById(userId);
        }

        return UrlPath.REDIRECT + UrlPath.USERS;
    }
}
