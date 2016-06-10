package by.epam.likeit.service.impl;

import by.epam.likeit.dao.UserDAO;
import by.epam.likeit.dao.UserDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Role;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.UserService;
import by.epam.likeit.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.CORBA.INVALID_ACTIVITY;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String loginPattern = "^\\w+$";
    private static final String INVALID_LOGIN = "invalid_login";
    private static final String INVALID_PASSWORD = "invalid_password";
    private static final String SPACE = " ";
    private static final String EMPTY = "";

    private String validateLogin(String login){
        String error = EMPTY;
        Pattern pattern = Pattern.compile(loginPattern);
        Matcher matcher = pattern.matcher(login);
        if(login.length() < 5 && !matcher.find()){
            error = INVALID_LOGIN;
        }
        return error;
    }

    private String validatePassword(String password){
        String error = EMPTY;
        if(password.length() < 5){
            error = INVALID_PASSWORD;
        }
        return error;
    }

    @Override
    public User loginUser(String login, String password) throws ServiceException {
        User user = null;
        try {
            UserDAO userDAO = UserDAOFactory.getInstance();
            user = userDAO.retrieve(login);
            if(user == null)
                LOGGER.trace("null");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return password.equals(user.getPassword()) ? user : null;
    }

    @Override
    public User registerUser(String login, String password, String name, String surname, String email, String role) throws ServiceException {
        User user = null;

        try {
            user = new User(login, password, name, surname, Role.valueOf(role.toUpperCase()), email, "");
            UserDAO userDAO = UserDAOFactory.getInstance();
            userDAO.create(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public void changePassword(String login, String oldPassword, String newPassword, String repeatNewPassword) throws ServiceException {
        UserDAO userDAO = UserDAOFactory.getInstance();

        try {
            String password = userDAO.retrieveUserPassword(login);
            if(password.equals(oldPassword) && newPassword.equals(repeatNewPassword)){
               // LOGGER.trace(login);
                userDAO.updatePassword(login, newPassword);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
