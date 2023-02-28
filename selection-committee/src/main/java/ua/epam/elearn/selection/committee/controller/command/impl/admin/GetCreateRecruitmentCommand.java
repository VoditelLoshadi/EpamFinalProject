package ua.epam.elearn.selection.committee.controller.command.impl.admin;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.path.JspFilePath;



public class GetCreateRecruitmentCommand implements Command {

    private static final String FACULTY_ID = "facultyId";

    @Override
    public String execute(HttpServletRequest request) {

        request.setAttribute(FACULTY_ID, request.getParameter(FACULTY_ID));

        return JspFilePath.ADD_RECRUITMENT;

    }
}
