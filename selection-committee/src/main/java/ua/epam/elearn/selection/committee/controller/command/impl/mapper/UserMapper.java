package ua.epam.elearn.selection.committee.controller.command.impl.mapper;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.model.dto.UserDto;



public class UserMapper {
    private static final String LOGIN = "login";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String PASSWORD_COPY = "passwordCopy";
    private static final String FIRST_NAME = "firstName";
    private static final String SECOND_NAME = "secondName";
    private static final String PATRONYMIC = "patronymic";
    private static final String CITY = "city";
    private static final String REGION = "region";
    private static final String INSTITUTION = "institution";

    public UserDto fetchUserDtoFromRequest(HttpServletRequest req) {
        return new UserDto(
                req.getParameter(LOGIN),
                req.getParameter(EMAIL),
                req.getParameter(PASSWORD),
                req.getParameter(PASSWORD_COPY),
                req.getParameter(FIRST_NAME),
                req.getParameter(SECOND_NAME),
                req.getParameter(PATRONYMIC),
                req.getParameter(CITY),
                req.getParameter(REGION),
                req.getParameter(INSTITUTION)
        );
    }

    public void insertUserDtoIntoRequest(UserDto userDto, HttpServletRequest req) {
        req.setAttribute(LOGIN, userDto.getLogin());
        req.setAttribute(FIRST_NAME, userDto.getFirstName());
        req.setAttribute(SECOND_NAME, userDto.getSecondName());
        req.setAttribute(EMAIL, userDto.getEmail());
        req.setAttribute(PATRONYMIC, userDto.getPatronymic());
        req.setAttribute(CITY, userDto.getCity());
    }

}
