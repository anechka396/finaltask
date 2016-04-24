package by.epam.likeit.service;

import by.epam.likeit.dao.UserDAO;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.dao.impl.UserDAOImpl;
import by.epam.likeit.entity.Role;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.exception.ServiceException;

public final class UserService {

    public final static User checkLogin(String login, String password) throws ServiceException{
        if (!Validator.loginValidator(login, password)){
            throw new ServiceException("Invalid login or password");
        }

        UserDAO dao = new UserDAOImpl();
        User user = null;
        try {
            user = dao.retrieve(login);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return password.equals(user.getPassword()) ? user : null;
    }

    public final static User addUser(String login, String password, String repeatPassword,
                                     String name, String email, String role) throws ServiceException {
        if (!Validator.loginValidator(login, password)){
            throw new ServiceException("Invalid login or password");
        }

        UserDAO dao = new UserDAOImpl();
        User user = new User(login, password, name, Role.valueOf(role.toUpperCase()), email);
        dao.create(user);
        return user;
    }

    static class Validator{
        public static boolean loginValidator(String login, String password){
            if(login.isEmpty()){
                return false;
            }
            if(password.isEmpty()){
                return false;
            }
            return true;
        }
    }
}
