package by.epam.likeit.service;

import by.epam.likeit.dao.UserDAO;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.dao.impl.UserDAOImpl;
import by.epam.likeit.entity.Role;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.exception.ServiceException;

public class RegistrationService {
    private static UserDAO userDAO = new UserDAOImpl();

    public User service(String login, String password, String name, String email, String role) throws ServiceException {
        User user = null;

        if(!ValidateService.validateLogin(login) || ! ValidateService.validatePassword(password)){
            throw new ServiceException("Invalid login or password!");
        }

        try {
            user = new User(login, password, name, Role.valueOf(role.toUpperCase()), email);
            userDAO.create(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return user;
    }
}
