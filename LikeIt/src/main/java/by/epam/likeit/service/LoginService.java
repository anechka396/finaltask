package by.epam.likeit.service;

import by.epam.likeit.dao.UserDAO;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.dao.impl.UserDAOImpl;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.exception.ServiceException;

/**
 * Created by Пользователь on 24.04.2016.
 */
public class LoginService {
    private static UserDAO userDAO = new UserDAOImpl();

    public User service(String login, String password) throws ServiceException {

        if(!ValidateService.validateLogin(login) || ! ValidateService.validatePassword(password)){
            throw new ServiceException("Invalid login or password!");
        }

        User user = null;
        try {
            user = userDAO.retrieve(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return password.equals(user.getPassword()) ? user : null;
    }
}
