package ua.epam.elearn.selection.committee.controller.validator;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.elearn.selection.committee.controller.exception.user.*;
import ua.epam.elearn.selection.committee.controller.validator.exceptions.UserExceptions;
import ua.epam.elearn.selection.committee.controller.validator.regexp.RegExp;
import ua.epam.elearn.selection.committee.model.dto.UserDto;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.epam.elearn.selection.committee.controller.validator.UserFieldLimit.*;

public class UserValidator {

    private static final Logger logger = LoggerFactory.getLogger(UserValidator.class);

    private UserValidator() {
    }


    public static boolean validate(UserDto userDto, HttpServletRequest request) {
        try {
            checkLoginSize(userDto.getLogin());
            checkEmailSize(userDto.getEmail());
            checkForEmailMatchingTemplate(userDto.getEmail());
            checkPasswordSize(userDto.getPassword());
            checkPasswordsIdentical(userDto.getPassword(), userDto.getPasswordCopy());
            checkForPasswordMatchTemplate(userDto.getPassword());
            checkFirstNameSize(userDto.getFirstName());
            checkSecondNameSize(userDto.getSecondName());
            checkPatronymicSize(userDto.getPatronymic());
            checkCitySize(userDto.getCity());
            checkRegionSize(userDto.getRegion());
            checkInstitutionSize(userDto.getInstitution());
            return true;
        } catch (PasswordsNotSameException e) {
            logger.warn("Passwords not same");
            request.setAttribute(UserExceptions.PASSWORDS_NOT_SAME, true);
        } catch (LoginSizeOutOfBoundsException e) {
            logger.warn("Login size out of bounds ({})", userDto.getLogin());
            request.setAttribute(UserExceptions.LOGIN_SIZE_OUT_OF_BOUNDS, true);
        } catch (PasswordSizeOutOfBoundsException e) {
            logger.warn("Password size out of bounds ({})", userDto.getPassword());
            request.setAttribute(UserExceptions.PASSWORD_SIZE_OUT_OF_BOUNDS, true);
        } catch (FirstNameSizeOutOfBoundsException e) {
            logger.warn("First name size out of bounds ({})", userDto.getFirstName());
            request.setAttribute(UserExceptions.FIRST_NAME_SIZE_OUT_OF_BOUNDS, true);
        } catch (SecondNameSizeOutOfBoundsException e) {
            logger.warn("Second name size out of bounds ({})", userDto.getSecondName());
            request.setAttribute(UserExceptions.SECOND_NAME_SIZE_OUT_OF_BOUNDS, true);
        } catch (InstitutionSizeOutOfBoundsException e) {
            logger.warn("Institution size out of bounds ({})", userDto.getInstitution());
            request.setAttribute(UserExceptions.INSTITUTION_SIZE_OUT_OF_BOUNDS, true);
        } catch (RegionSizeOutOfBoundsException e) {
            logger.warn("Region size out of bounds ({})", userDto.getRegion());
            request.setAttribute(UserExceptions.REGION_SIZE_OUT_OF_BOUNDS, true);
        } catch (PatronymicSizeOutOfBoundsException e) {
            logger.warn("Patronymic size out of bounds ({})", userDto.getPatronymic());
            request.setAttribute(UserExceptions.PATRONYMIC_SIZE_OUT_OF_BOUNDS, true);
        } catch (CitySizeOutOfBoundsException e) {
            logger.warn("City size out of bounds ({})", userDto.getCity());
            request.setAttribute(UserExceptions.CITY_SIZE_OUT_OF_BOUNDS, true);
        } catch (PasswordNotMatchTemplateException e) {
            logger.warn("Password not match template ({})", userDto.getPassword());
            request.setAttribute(UserExceptions.PASSWORD_NOT_MATCH_TEMPLATE, true);
        } catch (EmailNotMatchTemplateException e) {
            logger.warn("Email not match template ({})", userDto.getEmail());
            request.setAttribute(UserExceptions.EMAIL_NOT_MATCH_TEMPLATE, true);
        } catch (EmailSizeOutOfBoundsException e) {
            logger.warn("Email size out of bounds ({})", userDto.getEmail());
            request.setAttribute(UserExceptions.EMAIL_SIZE_OUT_OF_BOUNDS, true);
        }
        return false;
    }

    private static void checkPasswordsIdentical(String password, String passwordCopy) throws PasswordsNotSameException {
        if (!password.equals(passwordCopy)) {
            throw new PasswordsNotSameException();
        }
    }

    private static void checkLoginSize(String login) throws LoginSizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(login)) throw new LoginSizeOutOfBoundsException();

        final int SIZE = login.length();

        if (SIZE < LOGIN_MIN_SIZE || SIZE > LOGIN_MAX_SIZE) {
            throw new LoginSizeOutOfBoundsException();
        }
    }

    private static void checkEmailSize(String email) throws EmailSizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(email)) throw new EmailSizeOutOfBoundsException();

        final int SIZE = email.length();

        if (SIZE < EMAIL_MIN_SIZE || SIZE > EMAIL_MAX_SIZE) {
            throw new EmailSizeOutOfBoundsException();
        }
    }

    private static void checkPasswordSize(String password) throws PasswordSizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(password)) throw new PasswordSizeOutOfBoundsException();

        final int SIZE = password.length();

        if (SIZE < PASSWORD_MIN_SIZE || SIZE > PASSWORD_MAX_SIZE) {
            throw new PasswordSizeOutOfBoundsException();
        }
    }

    private static void checkFirstNameSize(String firstName) throws FirstNameSizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(firstName)) throw new FirstNameSizeOutOfBoundsException();

        if (firstName.length() > FIRST_NAME_MAX_SIZE) {
            throw new FirstNameSizeOutOfBoundsException();
        }
    }

    private static void checkSecondNameSize(String secondName) throws SecondNameSizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(secondName)) throw new SecondNameSizeOutOfBoundsException();

        if (secondName.length() > SECOND_NAME_MAX_SIZE) {
            throw new SecondNameSizeOutOfBoundsException();
        }
    }

    private static void checkPatronymicSize(String patronymic) throws PatronymicSizeOutOfBoundsException {
        if (patronymic.length() > PATRONYMIC_MAX_SIZE) {
            throw new PatronymicSizeOutOfBoundsException();
        }
    }

    private static void checkCitySize(String city) throws CitySizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(city)) throw new CitySizeOutOfBoundsException();
        if (city.length() > CITY_MAX_SIZE) {
            throw new CitySizeOutOfBoundsException();
        }
    }

    private static void checkRegionSize(String region) throws RegionSizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(region)) throw new RegionSizeOutOfBoundsException();
        if (region.length() > REGION_MAX_SIZE) {
            throw new RegionSizeOutOfBoundsException();
        }
    }

    private static void checkInstitutionSize(String institution) throws InstitutionSizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(institution)) throw new InstitutionSizeOutOfBoundsException();
        if (institution.length() > INSTITUTION_MAX_SIZE) {
            throw new InstitutionSizeOutOfBoundsException();
        }
    }

    private static void checkForPasswordMatchTemplate(String password) throws PasswordNotMatchTemplateException {
        Pattern pattern = Pattern.compile(RegExp.PASSWORD);
        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()) {
            throw new PasswordNotMatchTemplateException();
        }
    }

    private static void checkForEmailMatchingTemplate(String email) throws EmailNotMatchTemplateException {
        Pattern pattern = Pattern.compile(RegExp.EMAIL);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new EmailNotMatchTemplateException();
        }
    }

}
