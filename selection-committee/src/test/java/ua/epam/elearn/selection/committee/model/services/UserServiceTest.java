package ua.epam.elearn.selection.committee.model.services;

import org.junit.Before;
import org.junit.Test;
import ua.epam.elearn.selection.committee.model.dao.UserDao;
import ua.epam.elearn.selection.committee.model.dto.UserDto;
import ua.epam.elearn.selection.committee.model.entity.User;
import ua.epam.elearn.selection.committee.model.entity.enums.Role;
import ua.epam.elearn.selection.committee.model.exception.user.AuthenticationException;
import ua.epam.elearn.selection.committee.model.exception.user.EmailIsReservedException;
import ua.epam.elearn.selection.committee.model.exception.user.LoginIsReservedException;
import ua.epam.elearn.selection.committee.model.exception.user.UserIsBlockedException;
import ua.epam.elearn.selection.committee.model.services.util.SHA256PasswordEncoder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UserServiceTest {

    private UserService userService;
    private SHA256PasswordEncoder passwordEncoder;
    private UserDao userDao;

    private final static String LOGIN = "VlaDdos";
    private final static String EMAIL = "v.redko@gmail.com";
    private final static String RANDOM_PASSWORD = "Password123";
    private final static String HASHED_PASSWORD = "008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601";
    private final static String FIRST_NAME = "Vladislav";
    private final static String SECOND_NAME = "Redko";
    private final static String PATRONYMIC = "Dmitrievich";
    private final static String CITY = "Kiev";
    private final static String REGION = "Kiev";
    private final static String INSTITUTION = "School 163";

    private User USER;
    private UserDto USER_DTO;

    @Before
    public void setUp() throws Exception {
        userDao = mock(UserDao.class);
        passwordEncoder = new SHA256PasswordEncoder();
        userService = new UserService(passwordEncoder, userDao);

        USER = new User.Builder()
                .addLogin(LOGIN)
                .addEmail(EMAIL)
                .addPassword(HASHED_PASSWORD)
                .addFirstName(FIRST_NAME)
                .addSecondName(SECOND_NAME)
                .addPatronymic(PATRONYMIC)
                .addCity(CITY)
                .addRegion(REGION)
                .addInstitution(INSTITUTION)
                .addBlocked(false)
                .build();

        USER_DTO = new UserDto(
                LOGIN,
                EMAIL,
                RANDOM_PASSWORD,
                RANDOM_PASSWORD,
                FIRST_NAME,
                SECOND_NAME,
                PATRONYMIC,
                CITY,
                REGION,
                INSTITUTION
        );
    }

    @Test
    public void checkGetUserById() {
        when(userDao.getUserById(100L)).thenReturn(USER);
        assertNotNull(userService.findById(100L));
    }

    @Test
    public void checkDoAuthenticationShouldNotThrowException() {
        String encodedPassword = passwordEncoder.encode(RANDOM_PASSWORD);
        when(userDao.getByLoginAndPassword(LOGIN, encodedPassword)).thenReturn(USER);

        assertDoesNotThrow(() -> userService.doAuthentication(LOGIN, RANDOM_PASSWORD));
    }

    @Test
    public void checkRegisterNewAccountShouldThrowLoginIsReservedException() {
        when(userDao.getUserByLogin(LOGIN)).thenReturn(new User(USER_DTO));
        when(userDao.getUserByEmail(EMAIL)).thenReturn(null);
        assertThrows(
                LoginIsReservedException.class,
                () -> userService.registerNewAccount(USER_DTO)
        );
    }

    @Test
    public void checkRegisterNewAccountShouldThrowEmailIsReservedException() {
        when(userDao.getUserByLogin(LOGIN)).thenReturn(null);
        when(userDao.getUserByEmail(EMAIL)).thenReturn(new User(USER_DTO));

        assertThrows(
                EmailIsReservedException.class,
                () -> userService.registerNewAccount(USER_DTO)
        );
    }

    @Test
    public void checkBlockById() {
        when(userDao.blockUserById(100L)).thenReturn(true);

        assertDoesNotThrow(() -> userService.blockById(100L));
    }

    @Test
    public void checkUnblockById() {
        when(userDao.unblockUserById(100L)).thenReturn(true);

        assertDoesNotThrow(() -> userService.unblockById(100L));
    }

    @Test
    public void checkGetRoleByRoleId() {
        when(userDao.getRoleByRoleId(100L)).thenReturn(Role.GUEST.toString());

        assertNotNull(userService.getRoleByRoleId(100L));

    }

    @Test
    public void checkGetCountPagesOfUsers() {
        when(userDao.getAllUsersSize()).thenReturn(4);
        assertEquals(userService.getCountPagesOfUsers(), 1);
    }

    @Test
    public void checkFindAllUsers() {
        when(userDao.getAllUsers()).thenReturn(Arrays.asList(USER));

        assertNotNull(userService.findAllUsers());
    }

    @Test
    public void checkRegisterNewAccountShouldWorkWithoutException() {
        when(userDao.getUserByLogin(LOGIN)).thenReturn(null);
        when(userDao.getUserByEmail(EMAIL)).thenReturn(null);

        assertDoesNotThrow(() -> userService.registerNewAccount(USER_DTO));

        verify(userDao, times(1)).createUser(USER);
    }

    @Test
    public void checkDoAuthenticationShouldThrowUserIsBlockedException() {
        USER.setBlocked(true);
        when(userDao.getByLoginAndPassword(LOGIN, HASHED_PASSWORD)).thenReturn(USER);

        assertThrows(
                UserIsBlockedException.class,
                () -> userService.doAuthentication(LOGIN, RANDOM_PASSWORD)
        );
    }

    @Test
    public void checkDoAuthenticationShouldThrowAuthenticationException() {
        when(userDao.getByLoginAndPassword(LOGIN, HASHED_PASSWORD)).thenReturn(null);

        assertThrows(
                AuthenticationException.class,
                () -> userService.doAuthentication(LOGIN, RANDOM_PASSWORD)
        );
    }


}
