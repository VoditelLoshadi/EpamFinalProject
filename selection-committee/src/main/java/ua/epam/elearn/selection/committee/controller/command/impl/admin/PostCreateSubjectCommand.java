package ua.epam.elearn.selection.committee.controller.command.impl.admin;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.command.impl.mapper.FacultyMapper;
import ua.epam.elearn.selection.committee.controller.command.impl.mapper.SubjectMapper;
import ua.epam.elearn.selection.committee.controller.path.JspFilePath;
import ua.epam.elearn.selection.committee.controller.path.UrlPath;
import ua.epam.elearn.selection.committee.controller.validator.FacultyValidator;
import ua.epam.elearn.selection.committee.controller.validator.SubjectValidator;
import ua.epam.elearn.selection.committee.model.dto.SubjectDto;
import ua.epam.elearn.selection.committee.model.exception.admin.FacultyNameIsReservedException;
import ua.epam.elearn.selection.committee.model.services.FacultyService;
import ua.epam.elearn.selection.committee.model.services.SubjectService;



public class PostCreateSubjectCommand implements Command {

    private static final String SUBJECT_NAME_IS_RESERVED_EXCEPTION = "subjectNameIsReserved";

    private final SubjectMapper subjectMapper = new SubjectMapper();

    private final SubjectService subjectService;

    public PostCreateSubjectCommand(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        SubjectDto subjectDto = subjectMapper.fetchSubjectDtoFromRequest(request);
        boolean subjectDtoIsValid = SubjectValidator.validate(subjectDto, request);

        if (subjectDtoIsValid) {
            subjectService.addNewSubject(subjectDto);
            return UrlPath.REDIRECT + UrlPath.SUBJECTS;

        }

        return JspFilePath.ADD_SUBJECT;
    }
}
