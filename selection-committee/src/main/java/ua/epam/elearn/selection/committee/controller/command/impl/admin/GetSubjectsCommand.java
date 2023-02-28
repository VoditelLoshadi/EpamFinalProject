package ua.epam.elearn.selection.committee.controller.command.impl.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.path.JspFilePath;
import ua.epam.elearn.selection.committee.model.entity.Faculty;
import ua.epam.elearn.selection.committee.model.entity.Subject;
import ua.epam.elearn.selection.committee.model.services.SubjectService;


import java.util.List;

public class GetSubjectsCommand implements Command {

    private static final String SUBJECT_LIST = "subjectList";

    SubjectService subjectService;

    public GetSubjectsCommand(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();

        List<Subject> subjectList = subjectService.getAllSubjects();

        session.setAttribute(SUBJECT_LIST, subjectList);

        return JspFilePath.SUBJECTS;

    }
}
