package ua.epam.elearn.selection.committee.controller.command.impl.common;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.path.JspFilePath;
import ua.epam.elearn.selection.committee.model.entity.Faculty;
import ua.epam.elearn.selection.committee.model.entity.Recruitment;
import ua.epam.elearn.selection.committee.model.entity.Subject;
import ua.epam.elearn.selection.committee.model.services.FacultyService;
import ua.epam.elearn.selection.committee.model.services.RecruitmentService;
import ua.epam.elearn.selection.committee.model.services.SubjectService;


import java.util.List;

public class GetFacultyCommand implements Command {

    private static final String FACULTY = "faculty";
    private static final String REQUIRED_SUBJECTS_LIST = "requiredSubjectList";
    private static final String RECRUITMENTS_LIST = "recruitmentsList";

    private final FacultyService facultyService;
    private final SubjectService subjectService;
    private final RecruitmentService recruitmentService;

    public GetFacultyCommand(FacultyService facultyService, SubjectService subjectService, RecruitmentService recruitmentService) {
        this.facultyService = facultyService;
        this.subjectService = subjectService;
        this.recruitmentService = recruitmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        Faculty faculty = facultyService.getFacultyById(Long.valueOf(request.getParameter("id")));

        List<Subject> requiredSubjectList = subjectService.getRequiredSubjects(faculty.getId());
        List<Recruitment> recruitmentsList = recruitmentService.getAllNowOpenedRecruitmentsByFacultyId(faculty.getId());

        request.setAttribute(FACULTY, faculty);
        request.setAttribute(REQUIRED_SUBJECTS_LIST, requiredSubjectList);
        request.setAttribute(RECRUITMENTS_LIST, recruitmentsList);

        return JspFilePath.FACULTY;
    }
}
