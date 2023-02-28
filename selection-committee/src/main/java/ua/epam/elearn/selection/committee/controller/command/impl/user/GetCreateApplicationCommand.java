package ua.epam.elearn.selection.committee.controller.command.impl.user;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.path.JspFilePath;
import ua.epam.elearn.selection.committee.model.entity.Subject;
import ua.epam.elearn.selection.committee.model.services.SubjectService;


import java.util.List;

public class GetCreateApplicationCommand implements Command {

    private static final String RECRUITMENT_ID = "recruitmentId";
    private static final String REQUIRED_SUBJECTS_LIST = "requiredSubjectList";

    private final SubjectService subjectService;

    public GetCreateApplicationCommand(SubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @Override
    public String execute(HttpServletRequest request) {

        long recruitmentId = Long.parseLong(request.getParameter(RECRUITMENT_ID));

        List<Subject> requiredSubjectList = subjectService.getRequiredSubjectsByRecruitmentId(recruitmentId);

        request.setAttribute(REQUIRED_SUBJECTS_LIST, requiredSubjectList);

        request.setAttribute(RECRUITMENT_ID, recruitmentId);

        return JspFilePath.CREATE_APPLICATION;
    }
}
