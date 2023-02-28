package ua.epam.elearn.selection.committee.controller.command.impl.guest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.path.JspFilePath;
import ua.epam.elearn.selection.committee.controller.path.UrlPath;
import ua.epam.elearn.selection.committee.model.entity.User;
import ua.epam.elearn.selection.committee.model.exception.user.AuthenticationException;
import ua.epam.elearn.selection.committee.model.exception.user.UserIsBlockedException;
import ua.epam.elearn.selection.committee.model.services.UserService;



public class PostLoginCommand  implements Command {

    private static final String LOGIN = "username";
    private static final String PASSWORD = "password";

    private static final String USER = "user";
    private static final String ROLE = "role";

    private static final String AUTHENTICATION_EXCEPTION = "authenticationException";
    private static final String ACCOUNT_IS_BLOCKED_EXCEPTION = "accountIsBlocked";

    private final UserService userService;



    public PostLoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        HttpSession session = request.getSession();

        try {
            User user = userService.doAuthentication(login, password);


            session.setAttribute(ROLE, userService.getRoleByRoleId(user.getRoleId()));
            session.setAttribute(USER, user);

            return UrlPath.REDIRECT + UrlPath.HOME;

        } catch (AuthenticationException e) {
            request.setAttribute(AUTHENTICATION_EXCEPTION, true);
        } catch (UserIsBlockedException e) {
            request.setAttribute(ACCOUNT_IS_BLOCKED_EXCEPTION, true);
        }

        return JspFilePath.LOGIN;
    }
}
