package ua.epam.elearn.selection.committee.controller.command.impl.common;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.path.JspFilePath;
import ua.epam.elearn.selection.committee.controller.path.UrlPath;
import ua.epam.elearn.selection.committee.model.entity.Application;
import ua.epam.elearn.selection.committee.model.entity.Recruitment;
import ua.epam.elearn.selection.committee.model.services.ApplicationService;
import ua.epam.elearn.selection.committee.model.services.RecruitmentService;


import java.util.List;

public class GetRecruitmentCommand implements Command {
    private static final String RECRUITMENTS_ID = "recruitmentId";
    private static final String APPLICATION_LIST = "applicationList";
    private static final String RECRUITMENT = "recruitment";

    private final RecruitmentService recruitmentService;
    private final ApplicationService applicationService;

    public GetRecruitmentCommand(RecruitmentService recruitmentService, ApplicationService applicationService) {
        this.recruitmentService = recruitmentService;
        this.applicationService = applicationService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        long recruitmentId = Long.parseLong(request.getParameter(RECRUITMENTS_ID));

        Recruitment recruitment = recruitmentService.getRecruitment(recruitmentId);

        if (recruitment == null)
            return UrlPath.REDIRECT + UrlPath.RECRUITMENTS;

        List<Application> applications = applicationService.getAllApplicationsByRecruitmentId(recruitment.getId());


        request.setAttribute(APPLICATION_LIST, applications);
        request.setAttribute(RECRUITMENT, recruitment);

        return JspFilePath.RECRUITMENT;
    }
}
