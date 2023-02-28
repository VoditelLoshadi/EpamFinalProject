package ua.epam.elearn.selection.committee.controller.command.impl.admin;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.command.impl.mapper.FacultyMapper;
import ua.epam.elearn.selection.committee.controller.command.impl.mapper.SubjectMapper;
import ua.epam.elearn.selection.committee.controller.path.JspFilePath;
import ua.epam.elearn.selection.committee.model.dto.FacultyDto;
import ua.epam.elearn.selection.committee.model.entity.Faculty;
import ua.epam.elearn.selection.committee.model.entity.Subject;
import ua.epam.elearn.selection.committee.model.services.FacultyService;
import ua.epam.elearn.selection.committee.model.services.SubjectService;


import java.util.List;

public class GetUpdateFacultyCommand implements Command {
    private final String FACULTY_ID = "facultyId";
    private final String SUBJECT_LIST = "subjectList";
    private final String CURRENT_SUBJECT_LIST = "currentSubjectList";

    private final FacultyMapper facultyInfoMapper = new FacultyMapper();
    private final SubjectMapper subjectMapper = new SubjectMapper();

    private final FacultyService facultyService;
    private final SubjectService subjectService;

    public GetUpdateFacultyCommand(FacultyService facultyService, SubjectService subjectService) {
        this.facultyService = facultyService;
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        Long facultyId = Long.valueOf(request.getParameter(FACULTY_ID));

        Faculty faculty = facultyService.getFacultyById(facultyId);
        List<Subject> subjectList = subjectService.getAllSubjects();
        List<Subject> currentSubjectList = subjectService.getRequiredSubjects(faculty.getId());

        request.setAttribute(SUBJECT_LIST, subjectList);
        request.setAttribute(CURRENT_SUBJECT_LIST, currentSubjectList);
        facultyInfoMapper.insertFacultyIntoRequest(faculty, request);

        return JspFilePath.CHANGE_FACULTY;
    }
}
