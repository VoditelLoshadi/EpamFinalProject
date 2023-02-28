package ua.epam.elearn.selection.committee.controller.path;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class UrlPath {
    private UrlPath() {
    }

    @All
    public static final String REDIRECT = "redirect:";

    @Admin
    public static final String ADD_SUBJECT = "/add_subject";
    @Admin
    public static final String ADD_RECRUITMENT = "/add_recruitment";
    @Admin
    public static final String SUBJECTS = "/subjects";
    @Admin
    public static final String USERS = "/users";
    @Admin
    public static final String ADD_FACULTY = "/add_faculty";

    @Admin
    public static final String CLOSE_RECRUITMENT = "/close-recruitment";

    @User
    public static final String SUBMIT_APPLICATION = "/submit-application";
    @User
    public static final String CREATE_APPLICATION = "/create-application";
    @All
    public static final String APPLICATION = "/application";
    @Guest
    public static final String LOGIN = "/login";
    @Guest
    public static final String REGISTRATION = "/registration";


    @Admin
    @User
    public static final String LOGOUT = "/logout";

    @Admin
    @User
    public static final String PROFILE = "/profile";

    @All
    public static final String HOME = "/";
    @All
    public static final String FACULTIES = "/faculties";
    @All
    public static final String FACULTY = "/view_faculty";

    @Admin
    public static final String DELETE_FACULTY = FACULTY + "/delete";
    @Admin
    public static final String CHANGE_FACULTY = "/change-faculty";

    @All
    public static final String RECRUITMENTS = "/recruitments";
    @All
    public static final String RECRUITMENT = "/recruitment";
    @All
    public static final String STATIC_RESOURCES = "/static";

}
