package by.epam.likeit.service;

import by.epam.likeit.dao.UserDAO;
import by.epam.likeit.dao.impl.UserDAOImpl;
import by.epam.likeit.entity.Role;
import by.epam.likeit.entity.User;

/**
 * Created by Пользователь on 24.04.2016.
 */
public class RegistrationService {
    private static UserDAO userDAO = new UserDAOImpl();

    public User service(String login, String password, String name, String email, String role){
        User user = new User(login, password, name, Role.valueOf(role.toUpperCase()), email);
        userDAO.create(user);
        return user;
    }
}
