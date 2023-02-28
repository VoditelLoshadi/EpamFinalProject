package ua.epam.elearn.selection.committee.controller.command.impl.guest;

import jakarta.servlet.http.HttpServletRequest;
import ua.epam.elearn.selection.committee.controller.command.Command;
import ua.epam.elearn.selection.committee.controller.command.impl.mapper.UserMapper;
import ua.epam.elearn.selection.committee.controller.path.JspFilePath;
import ua.epam.elearn.selection.committee.controller.path.UrlPath;
import ua.epam.elearn.selection.committee.controller.validator.UserValidator;
import ua.epam.elearn.selection.committee.model.dto.UserDto;
import ua.epam.elearn.selection.committee.model.exception.user.EmailIsReservedException;
import ua.epam.elearn.selection.committee.model.exception.user.LoginIsReservedException;
import ua.epam.elearn.selection.committee.model.services.UserService;



public class PostRegistrationCommand implements Command {

    private static final String LOGIN_IS_RESERVED_EXCEPTION = "loginIsReserved";
    private static final String EMAIL_IS_RESERVED_EXCEPTION = "emailIsReserved";

    private final UserMapper userInfoMapper = new UserMapper();
    private final UserService userService;

    public PostRegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        UserDto userDto = userInfoMapper.fetchUserDtoFromRequest(request);

        boolean userDtoIsValid = UserValidator.validate(userDto, request);

        if (userDtoIsValid) {
            try {
                userService.registerNewAccount(userDto);

                return UrlPath.REDIRECT + UrlPath.LOGIN;
            } catch (LoginIsReservedException e) {
                request.setAttribute(LOGIN_IS_RESERVED_EXCEPTION, true);
            } catch (EmailIsReservedException e) {
                request.setAttribute(EMAIL_IS_RESERVED_EXCEPTION, true);
            }


        }

        userInfoMapper.insertUserDtoIntoRequest(userDto, request);
        return JspFilePath.REGISTRATION;
    }


}
