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

public class PostCreateFacultyCommand implements Command {

    private final String FACULTY_NAME_IS_RESERVED_EXCEPTION = "facultyNameIsReserved";
    private final String SUBJECT_LIST = "subjectList";

    private final FacultyMapper facultyInfoMapper = new FacultyMapper();
    private final SubjectMapper subjectMapper = new SubjectMapper();

    private final FacultyService facultyService;
    private final SubjectService subjectService;

    public PostCreateFacultyCommand(FacultyService facultyService, SubjectService subjectService) {
        this.facultyService = facultyService;
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        FacultyDto facultyDto = facultyInfoMapper.fetchFacultyDtoWithSubjectsFromRequest(request);

        boolean facultyDtoIsValid = FacultyValidator.validate(facultyDto, request);


        if (facultyDtoIsValid) {

            try {
                facultyService.addNewFaculty(facultyDto);

                subjectService.addRequiredSubjects(
                        facultyService.findFacultyByName(facultyDto.getName()).getId(),
                        subjectMapper.fetchSubjectListDtoFromRequest(request)
                );

                return UrlPath.REDIRECT + UrlPath.FACULTIES;

            } catch (FacultyNameIsReservedException e) {
                request.setAttribute(FACULTY_NAME_IS_RESERVED_EXCEPTION, true);
            }
        }

        List<Subject> subjectList = subjectService.getAllSubjects();
        request.setAttribute(SUBJECT_LIST, subjectList);
        return JspFilePath.ADD_FACULTY;

    }
}
