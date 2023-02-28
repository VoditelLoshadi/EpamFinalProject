package ua.epam.elearn.selection.committee.controller.command.impl.common;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.path.JspFilePath;
import ua.epam.elearn.selection.committee.controller.path.UrlPath;
import ua.epam.elearn.selection.committee.model.entity.Application;
import ua.epam.elearn.selection.committee.model.entity.Recruitment;
import ua.epam.elearn.selection.committee.model.services.ApplicationService;
import ua.epam.elearn.selection.committee.model.services.RecruitmentService;
import ua.epam.elearn.selection.committee.model.services.ServiceFactory;



public class GetApplicationCommand implements Command {
    private static final String APPLICATION_ID = "id";
    private static final String APPLICATION = "application";
    private static final String RECRUITMENT = "recruitment";

    ApplicationService applicationService;
    RecruitmentService recruitmentService;

    public GetApplicationCommand(ApplicationService applicationService) {
        this.applicationService = applicationService;
        recruitmentService = ServiceFactory.getInstance().createRecruitmentService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        long applicationId = Long.parseLong(request.getParameter(APPLICATION_ID));

        Application application = applicationService.getApplication(applicationId);

        if (application == null)
            return UrlPath.REDIRECT + UrlPath.RECRUITMENTS;

        Recruitment recruitment = recruitmentService.getRecruitmentByApplicationId(application.getId());

        request.setAttribute(APPLICATION, application);
        request.setAttribute(RECRUITMENT, recruitment);

        return JspFilePath.APPLICATION;
    }
}
