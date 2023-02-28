package ua.epam.elearn.selection.committee.controller.command.impl.common;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.path.UrlPath;
import ua.epam.elearn.selection.committee.model.entity.enums.Role;



public class GetLogoutCommand implements Command {

    private static final String USER_ID = "userId";
    private static final String USER = "user";
    private static final String ROLE = "role";

    @Override
    public String execute(HttpServletRequest request) {

        request.getSession().invalidate();
      //  request.getSession().setAttribute(USER, null);
       // request.getSession().setAttribute(ROLE, null);

        return UrlPath.REDIRECT + UrlPath.HOME;
    }
}
