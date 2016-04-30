package by.epam.likeit.service.impl;

import by.epam.likeit.dao.UserDAO;
import by.epam.likeit.dao.UserDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.dao.impl.UserDAOImpl;
import by.epam.likeit.entity.Role;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.AbsractService;
import by.epam.likeit.service.exception.ServiceException;

public class RegistrationService extends AbsractService<User> {

    @Override
    public User service(String login, String password, String name, String email, String role) throws ServiceException {
        User user = null;

        try {
            user = new User(login, password, name, Role.valueOf(role.toUpperCase()), email);
            UserDAO userDAO = UserDAOFactory.getInstance();
            userDAO.create(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }
}
