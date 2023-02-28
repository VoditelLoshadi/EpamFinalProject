package ua.epam.elearn.selection.committee.model.services;


import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.elearn.selection.committee.model.dao.UserDao;
import ua.epam.elearn.selection.committee.model.dto.UserDto;
import ua.epam.elearn.selection.committee.model.entity.User;
import ua.epam.elearn.selection.committee.model.exception.user.AuthenticationException;
import ua.epam.elearn.selection.committee.model.exception.user.EmailIsReservedException;
import ua.epam.elearn.selection.committee.model.exception.user.LoginIsReservedException;
import ua.epam.elearn.selection.committee.model.exception.user.UserIsBlockedException;
import ua.epam.elearn.selection.committee.model.services.util.PasswordEncoder;

import java.util.List;

/**
 * Manages business logic related with User.
 *
 * @author Nikita Gamaiunov
 */
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final int PAGE_SIZE = 10;

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder, UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    public int getCountPagesOfUsers() {
        return (int) Math.ceil(userDao.getAllUsersSize()  / (double) PAGE_SIZE);
    }

    public User findById(Long id) {
        return userDao.getUserById(id);
    }

    public List<User> findAllUsers() {
        return userDao.getAllUsers();
    }

    public String getRoleByRoleId(long roleId){
        return userDao.getRoleByRoleId(roleId);
    }

    public List<User> getPaginationAllUsers(String order, int pageNum) {
        int offset = PAGE_SIZE * (pageNum - 1) ;
        return userDao.getPaginationAllUsers(order, PAGE_SIZE, offset);
    }

    /**
     * Process authentication.
     *
     * @param username String representing username.
     * @param password String representing  not encoded password.
     * @return User instance representing that authentication has been done successful.
     * @throws UserIsBlockedException  Indicates that User is blocked.
     * @throws AuthenticationException Indicates that credentials are incorrect.
     */
    public User doAuthentication(String username, String password) throws UserIsBlockedException, AuthenticationException {

        password = passwordEncoder.encode(password.trim());
        User user = userDao.getByLoginAndPassword(username, password);

        if (user != null) {
            if (!user.isBlocked()) {
                return user;
            } else {
                throw new UserIsBlockedException();
            }
        } else {
            throw new AuthenticationException();
        }
    }

    /**
     * Process creating new user account.
     *
     * @param userDto UserDto instance.
     * @throws LoginIsReservedException Indicates that username is reserved.
     * @throws EmailIsReservedException Indicates that email is reserved.
     */
    public void registerNewAccount(UserDto userDto) throws LoginIsReservedException, EmailIsReservedException {

        checkLoginIsUnique(userDto.getLogin());
        checkEmailIsUnique(userDto.getEmail());

        User user = new User(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword().trim()));

        userDao.createUser(user);
        logger.info("New account {} has been created", user);
    }


    public void blockById(Long id) {
        userDao.blockUserById(id);
        logger.info("User ({}) has been blocked", id);
    }

    public void unblockById(Long id) {
        userDao.unblockUserById(id);
        logger.info("User ({}) has been unblocked", id);
    }

    private void checkLoginIsUnique(String login) throws LoginIsReservedException {
        if (userDao.getUserByLogin(login) != null) throw new LoginIsReservedException();
    }

    private void checkEmailIsUnique(String email) throws EmailIsReservedException {
        if (userDao.getUserByEmail(email) != null) throw new EmailIsReservedException();
    }

}
