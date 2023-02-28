package ua.epam.elearn.selection.committee.controller.command.impl.admin;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.path.UrlPath;
import ua.epam.elearn.selection.committee.model.services.FacultyService;



public class PostDeleteFacultyCommand implements Command {

    private static final String FACULTY_ID = "facultyId";

    private final FacultyService facultyService;

    public PostDeleteFacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long facultyId = Long.valueOf(request.getParameter(FACULTY_ID));

        if (!facultyService.isExistedByFacultyId(facultyId)) {
            return UrlPath.REDIRECT + UrlPath.FACULTIES;
        }

        facultyService.deleteFaculty(facultyId);

        return UrlPath.REDIRECT + UrlPath.FACULTIES;
    }
}
