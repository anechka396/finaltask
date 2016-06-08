package by.epam.likeit.service.impl;

import by.epam.likeit.dao.UserDAO;
import by.epam.likeit.dao.UserDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Role;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.UserService;
import by.epam.likeit.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

    @Override
    public User loginUser(String login, String password) throws ServiceException {
        User user = null;
        try {
            UserDAO userDAO = UserDAOFactory.getInstance();
            user = userDAO.retrieve(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return password.equals(user.getPassword()) ? user : null;
    }

    @Override
    public User registerUser(String login, String password, String name, String surname, String email, String role) throws ServiceException {
        User user = null;

        try {
            user = new User(login, password, name, surname, Role.valueOf(role.toUpperCase()), email);
            UserDAO userDAO = UserDAOFactory.getInstance();
            userDAO.create(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return user;
    }
}
