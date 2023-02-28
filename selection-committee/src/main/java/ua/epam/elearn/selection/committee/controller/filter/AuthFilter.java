package ua.epam.elearn.selection.committee.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.epam.elearn.selection.committee.controller.path.*;
import ua.epam.elearn.selection.committee.model.entity.enums.Role;


import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthFilter implements Filter {

    private static final String ROLE_ATTRIBUTE = "role";
    private static final String USER_ID_ATTRIBUTE = "userId";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        final String URI = request.getRequestURI();

        if (session.getAttribute(ROLE_ATTRIBUTE) == null) {
            session.setAttribute(ROLE_ATTRIBUTE, Role.GUEST.toString());
        }

        Role role = Role.valueOf(session.getAttribute(ROLE_ATTRIBUTE).toString());



        /*if (!getUriPath().contains(URI) && !URI.contains(UrlPath.STATIC_RESOURCES)) {
            response.sendError(404);
            return;
        }*/


        if (!checkAccess(URI, role) && !URI.contains(UrlPath.STATIC_RESOURCES)) {
            if (role.equals(Role.GUEST)) {
                response.sendRedirect(UrlPath.LOGIN);
                return;
            }
            response.sendError(403);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean checkAccess(String uri, Role role) {
        Field[] allFields = UrlPath.class.getDeclaredFields();

        SampleClass sampleObject = new SampleClass();
        sampleObject.setSampleField("data");

        for (Field field : allFields) {
            try {
                if (((String) field.get(sampleObject)).equals(uri)) {
                    if (field.isAnnotationPresent(All.class))
                        return true;
                    if (role == Role.GUEST && field.isAnnotationPresent(Guest.class))
                        return true;
                    if (role == Role.CLIENT && field.isAnnotationPresent(User.class)){

                        return true;
                    }

                    if (role == Role.ADMIN && field.isAnnotationPresent(Admin.class)){
                        return true;
                    }

                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return false;
    }


    private List<String> getUriPath() {
        List<String> pathList = new ArrayList<>();

        Field[] allFields = UrlPath.class.getDeclaredFields();

        SampleClass sampleObject = new SampleClass();
        sampleObject.setSampleField("data");


        Arrays.stream(allFields).forEach(e -> {
            try {
                pathList.add((String) e.get(sampleObject));
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        });
        return pathList;
    }

    @CustomAnnotation(name = "SampleClass", value = "Sample Class Annotation")
    class SampleClass {

        @CustomAnnotation(name = "sampleClassField", value = "Sample Field Annotation")
        public String sampleField;

        public String getSampleField() {
            return sampleField;
        }

        public void setSampleField(String sampleField) {
            this.sampleField = sampleField;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface CustomAnnotation {
        public String name();

        public String value();
    }

    @Override
    public void destroy() {
    }
}
