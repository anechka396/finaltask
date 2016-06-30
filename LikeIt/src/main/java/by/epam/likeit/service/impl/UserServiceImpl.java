package by.epam.likeit.service.impl;

import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.dao.UserDAO;
import by.epam.likeit.dao.UserDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Role;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.UserService;
import by.epam.likeit.service.exception.ServiceException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.CORBA.INVALID_ACTIVITY;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String loginPattern = "^\\w+$";
    private static final String emailPattern = "^[-a-z0-9_]+@[a-z]+\\.[a-z]{2,4}$";
    private static final String URL = "url";
    private static final String EMPTY = "";
    private static final String ERROR_LOGIN = "prop.invalidLogin";
    private static final String ERROR_LOGIN_LENGTH = "prop.invalidLoginLength";
    private static final String ERROR_PASSWORD_LENGTH = "prop.invalidPasswordLength";
    private static final String ERROR_PASSWORD_DIFFERENT = "prop.differentPassword";
    private static final String ERROR_NAME_LENGTH = "prop.invalidNameLength";
    private static final String ERROR_SURNAME_LENGTH = "prop.invalidSurnameLength";
    private static final String ERROR_EMAIL = "prop.invalidEmail";
    private static final String ERROR_NO_SUCH_USER = "prop.noSuchUser";
    private static final String ERROR_PASSWORD = "prop.invalidPassword";

    private void validateLogin(String login) throws ServiceException {
        Pattern pattern = Pattern.compile(loginPattern);
        Matcher matcher = pattern.matcher(login);
        if(!matcher.find()){
            throw new ServiceException(ERROR_LOGIN);
        } else if(login.length() < 5){
            throw new ServiceException(ERROR_LOGIN_LENGTH);
        }
    }

    private void validatePassword(String password) throws ServiceException {
        if(password.length() < 5){
            throw  new ServiceException(ERROR_PASSWORD_LENGTH);
        }
    }

    private void validatePassword(String password, String repeatPassword) throws ServiceException {
        if(password.length() < 5){
            throw new ServiceException(ERROR_PASSWORD_LENGTH);
        } else if(!password.equals(repeatPassword)){
            throw new ServiceException(ERROR_PASSWORD_DIFFERENT);
        }
    }

    private void validateName(String name) throws ServiceException {
        if(name.length() < 2){
            throw new ServiceException(ERROR_NAME_LENGTH);
        }
    }

    private void  validateSurname(String surname) throws ServiceException {
        if(surname.length() < 2){
            throw new ServiceException(ERROR_SURNAME_LENGTH);
        }
    }

    private void validateEmail(String email) throws ServiceException {
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.find()){
            throw new ServiceException(ERROR_EMAIL);
        }
    }

    @Override
    public User loginUser(String login, String password) throws ServiceException {
        validateLogin(login);
        validatePassword(password);

        User user = null;
        try {
            UserDAO userDAO = UserDAOFactory.getInstance();
            user = userDAO.retrieve(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        if(user == null){
            throw new ServiceException(ERROR_NO_SUCH_USER);
        } else if (!password.equals(user.getPassword())){
            throw new ServiceException(ERROR_PASSWORD);
        }

        return  user;
    }

    @Override
    public User registerUser(String login, String password,String repeatPassword, String name, String surname, String email, String role) throws ServiceException {

        validateLogin(login);
        validatePassword(password, repeatPassword);
        validateName(name);
        validateSurname(surname);
        validateEmail(email);

        User user = null;

        try {
            user = new User(login, password, name, surname, Role.valueOf(role.toUpperCase()), email, EMPTY);
            UserDAO userDAO = UserDAOFactory.getInstance();
            userDAO.create(user);
        } catch (DaoException e) {
            String message = e.getMessage();
            if(!message.equals(EMPTY)) {
                throw new ServiceException(message);
            } else{
                throw new ServiceException(e);
            }
        }

        return user;
    }

    @Override
    public void changePassword(User user, String oldPassword, String newPassword, String repeatNewPassword) throws ServiceException {

        if(user == null){
            throw new ServiceException();
        }

        validatePassword(newPassword, repeatNewPassword);
        UserDAO userDAO = UserDAOFactory.getInstance();
        String login = user.getLogin();

        try {
            String password = userDAO.retrieveUserPassword(login);
            if(!password.equals(oldPassword)){
                throw new ServiceException(ERROR_PASSWORD);
            }

            if(password.equals(newPassword)){
                return;
            }

            userDAO.updatePassword(login, newPassword);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String changeImage(User user, File file) throws ServiceException {

        if(user == null){
            throw new ServiceException();
        }

        String imageURL = EMPTY;
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dgubd42xn",
                "api_key", "672941474238658",
                "api_secret", "IU8hDylaNIoTIARGBivQaRIqS4c"

        ));

        try {
            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            imageURL = (String) uploadResult.get(URL);
            Files.delete(file.toPath());
            UserDAO userDAO = UserDAOFactory.getInstance();
            userDAO.updateImage(user.getLogin(), imageURL);
        } catch (IOException e) {
            throw new ServiceException(e);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return imageURL;
    }

    @Override
    public User editUser(User user, String name, String surname, String email) throws ServiceException {
        if(user == null){
            throw new ServiceException();
        }

        String login = user.getLogin();

        validateName(name);
        validateSurname(surname);
        validateEmail(email);

        User newUser = null;
        try {
            newUser = new User();
            newUser.setLogin(login);
            newUser.setName(name);
            newUser.setLastName(surname);
            newUser.setEmail(email);
            UserDAO userDAO = UserDAOFactory.getInstance();
            userDAO.update(newUser);
            newUser = userDAO.retrieve(login);
        } catch (DaoException e) {
            String message = e.getMessage();
            if(!message.equals(EMPTY)) {
                throw new ServiceException(message);
            } else{
                throw new ServiceException(e);
            }
        }

        return  newUser;
    }
}
