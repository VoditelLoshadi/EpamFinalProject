package ua.epam.elearn.selection.committee.controller.command.impl.admin;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.path.JspFilePath;
import ua.epam.elearn.selection.committee.model.entity.Subject;
import ua.epam.elearn.selection.committee.model.services.SubjectService;


import java.util.List;

public class GetCreateSubjectCommand  implements Command {

    @Override
    public String execute(HttpServletRequest request) {


        return JspFilePath.ADD_SUBJECT;
    }
}
