package by.epam.likeit.dao;

import by.epam.likeit.dao.impl.UserDAOImpl;

/**
 * Returns the realization of class UserDAO.
 * @author Anna Yakubenko
 * @author anechka396@mail.ru
 * @version 1.0
 */
public class UserDAOFactory {
    private static UserDAO userDAO = new UserDAOImpl();

    public static UserDAO getInstance(){
        return userDAO;
    }
}
