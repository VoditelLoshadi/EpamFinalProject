package ua.epam.elearn.selection.committee.controller.command.impl.admin;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.command.impl.mapper.FacultyMapper;
import ua.epam.elearn.selection.committee.controller.command.impl.mapper.SubjectMapper;
import ua.epam.elearn.selection.committee.controller.path.JspFilePath;
import ua.epam.elearn.selection.committee.controller.path.UrlPath;
import ua.epam.elearn.selection.committee.controller.validator.FacultyValidator;
import ua.epam.elearn.selection.committee.model.dto.FacultyDto;
import ua.epam.elearn.selection.committee.model.entity.Subject;
import ua.epam.elearn.selection.committee.model.exception.admin.FacultyNameIsReservedException;
import ua.epam.elearn.selection.committee.model.services.FacultyService;
import ua.epam.elearn.selection.committee.model.services.SubjectService;

import java.util.List;

public class PostUpdateFacultyCommand implements Command {

    private final String FACULTY_ID = "facultyId";
    private final String SUBJECT_LIST = "subjectList";
    private final String CURRENT_SUBJECT_LIST = "currentSubjectList";
    private final String FACULTY_NAME_IS_RESERVED_EXCEPTION = "facultyNameIsReserved";

    private final FacultyService facultyService;
    private final SubjectService subjectService;

    private final FacultyMapper facultyInfoMapper = new FacultyMapper();
    private final SubjectMapper subjectMapper = new SubjectMapper();

    public PostUpdateFacultyCommand(FacultyService facultyService, SubjectService subjectService) {
        this.facultyService = facultyService;
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        Long facultyId = Long.valueOf(request.getParameter(FACULTY_ID));

        FacultyDto facultyDto = facultyInfoMapper.fetchFacultyDtoWithSubjectsFromRequest(request);
        facultyDto.setId(facultyId.toString());

        List<Subject> subjectList = subjectService.getAllSubjects();
        List<Subject> currentSubjectList = subjectService.getRequiredSubjects(facultyId);

        boolean facultyDtoIsValid = FacultyValidator.validate(facultyDto, request);

        if (facultyDtoIsValid) {

            try {
                facultyService.updateFaculty(facultyDto);


                subjectService.updateRequiredSubjects(
                        facultyId,
                        subjectMapper.fetchSubjectListDtoFromRequest(request)
                );


                return UrlPath.REDIRECT + UrlPath.FACULTIES;

            } catch (FacultyNameIsReservedException e) {
                request.setAttribute(FACULTY_NAME_IS_RESERVED_EXCEPTION, true);
            }
        }

        request.setAttribute(SUBJECT_LIST, subjectList);
        request.setAttribute(CURRENT_SUBJECT_LIST, currentSubjectList);
        facultyInfoMapper.insertFacultyDtoIntoRequest(facultyDto, request);

        return JspFilePath.CHANGE_FACULTY;


    }
}
