package by.epam.likeit.dao;


import by.epam.likeit.dao.impl.UserDAOImpl;

public class UserDAOFactory {
    private static UserDAO userDAO = new UserDAOImpl();

    public static UserDAO getInstance(){
        return userDAO;
    }
}
