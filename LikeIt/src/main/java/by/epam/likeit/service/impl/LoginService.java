package by.epam.likeit.service.impl;

import by.epam.likeit.dao.UserDAO;
import by.epam.likeit.dao.UserDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.AbstractService;
import by.epam.likeit.service.exception.ServiceException;

/**
 * Created by Пользователь on 24.04.2016.
 */
public class LoginService extends AbstractService<User> {

    @Override
    public User service(String login, String password) throws ServiceException {
        User user = null;
        try {
            UserDAO userDAO = UserDAOFactory.getInstance();
            user = userDAO.retrieve(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return password.equals(user.getPassword()) ? user : null;
    }
}
