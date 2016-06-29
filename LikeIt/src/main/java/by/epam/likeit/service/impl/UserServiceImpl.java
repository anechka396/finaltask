package by.epam.likeit.service.impl;

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
    private static final String ERROR_LOGIN_OR_PASSWORD = "errorLoginOrPassword";
    private static final String ERROR_PARAMS = "errorParams";
    private static final String URL = "url";
    private static final String EMPTY = "";

    private void validateLogin(String login) throws ServiceException {
        Pattern pattern = Pattern.compile(loginPattern);
        Matcher matcher = pattern.matcher(login);
        if(login.length() < 5 && !matcher.find()){
            throw new ServiceException();
        }
    }

    private void validatePassword(String password) throws ServiceException {
        if(password.length() < 5){
            throw  new ServiceException();
        }
    }

    private void validatePassword(String password, String repeatPassword) throws ServiceException {
        if(password.length() < 5 || !password.equals(repeatPassword)){
            throw new ServiceException();
        }
    }

    private void validateName(String name) throws ServiceException {
        if(name.length() < 2){
            throw new ServiceException();
        }
    }

    private void  validateSurname(String surname) throws ServiceException {
        if(surname.length() < 2){
            throw new ServiceException();
        }
    }

    private void validateEmail(String email) throws ServiceException {
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.find()){
            throw new ServiceException();
        }
    }

    @Override
    public User loginUser(String login, String password) throws ServiceException {
        try {
            validateLogin(login);
            validatePassword(password);
        } catch (ServiceException e){
            throw new ServiceException(ERROR_PARAMS);
        }

        User user = null;
        try {
            UserDAO userDAO = UserDAOFactory.getInstance();
            user = userDAO.retrieve(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        if(user == null || !password.equals(user.getPassword())){
            throw new ServiceException(ERROR_LOGIN_OR_PASSWORD);
        }

        return  user;
    }

    @Override
    public User registerUser(String login, String password,String repeatPassword, String name, String surname, String email, String role) throws ServiceException {
        try {
            validateLogin(login);
            validatePassword(password, repeatPassword);
            validateName(name);
            validateSurname(surname);
            validateEmail(email);
        } catch (ServiceException e){
            throw new ServiceException(ERROR_PARAMS);
        }

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
    public void changePassword(String login, String oldPassword, String newPassword, String repeatNewPassword) throws ServiceException {

        UserDAO userDAO = UserDAOFactory.getInstance();

        try {
            String password = userDAO.retrieveUserPassword(login);
            if(password.equals(oldPassword) && newPassword.equals(repeatNewPassword)){
                userDAO.updatePassword(login, newPassword);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String changeImage(String login, File file) throws ServiceException {
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
            userDAO.updateImage(login, imageURL);
        } catch (IOException e) {
            throw new ServiceException(e);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return imageURL;
    }

    @Override
    public User editUser(String login, String name, String surname, String email) throws ServiceException {
        try {
            validateName(name);
            validateSurname(surname);
            validateEmail(email);
        }catch (ServiceException e){
            throw new ServiceException(ERROR_PARAMS);
        }

        User user = null;
        try {
            user = new User();
            user.setLogin(login);
            user.setName(name);
            user.setLastName(surname);
            user.setEmail(email);
            UserDAO userDAO = UserDAOFactory.getInstance();
            userDAO.update(user);
            user = userDAO.retrieve(login);
        } catch (DaoException e) {
            String message = e.getMessage();
            if(!message.equals(EMPTY)) {
                throw new ServiceException(message);
            } else{
                throw new ServiceException(e);
            }
        }

        return  user;
    }
}
