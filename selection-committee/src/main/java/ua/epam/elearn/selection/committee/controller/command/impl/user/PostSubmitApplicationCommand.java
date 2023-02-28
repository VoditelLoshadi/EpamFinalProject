package ua.epam.elearn.selection.committee.controller.command.impl.user;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.command.impl.mapper.ApplicationMapper;
import ua.epam.elearn.selection.committee.controller.path.JspFilePath;
import ua.epam.elearn.selection.committee.controller.path.UrlPath;
import ua.epam.elearn.selection.committee.controller.validator.ApplicationValidator;
import ua.epam.elearn.selection.committee.model.dto.ApplicationDto;
import ua.epam.elearn.selection.committee.model.exception.user.UserAlreadyAppliedException;
import ua.epam.elearn.selection.committee.model.services.ApplicationService;
import ua.epam.elearn.selection.committee.model.services.SubjectService;



public class PostSubmitApplicationCommand implements Command {

    private static final String RECRUITMENT_ID = "recruitmentId";
    private static final String REQUIRED_SUBJECTS_LIST = "requiredSubjectList";
    private static final String USER_ALREADY_APPLIED = "UserAlreadyAppliedException";

    private final ApplicationService applicationService;
    private final SubjectService subjectService;

    private final ApplicationMapper applicationMapper = new ApplicationMapper();

    public PostSubmitApplicationCommand(ApplicationService applicationService, SubjectService subjectService) {
        this.applicationService = applicationService;
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        ApplicationDto applicationDto = applicationMapper.fetchApplicationWithGradeDtoFromRequest(request);

        boolean applicationDtoIsValid = ApplicationValidator.validate(applicationDto, request);

        if (applicationDtoIsValid) {
            try {
                applicationService.addNewApplication(applicationDto, subjectService);
            } catch (UserAlreadyAppliedException e) {
                request.setAttribute(USER_ALREADY_APPLIED, true);
            }

            return UrlPath.REDIRECT + UrlPath.RECRUITMENTS;
        }

        applicationMapper.insertApplicationDtoIntoRequest(applicationDto, request);
        request.setAttribute(REQUIRED_SUBJECTS_LIST,
                subjectService.getRequiredSubjectsByRecruitmentId(Long.parseLong(applicationDto.getRecruitmentId())));

        return JspFilePath.CREATE_APPLICATION;
    }


}
