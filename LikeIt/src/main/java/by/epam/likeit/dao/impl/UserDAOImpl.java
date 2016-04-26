package by.epam.likeit.dao.impl;

import by.epam.likeit.dao.UserDAO;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.dao.pool.ConnectionPool;
import by.epam.likeit.dao.pool.exception.ConnectionPoolException;
import by.epam.likeit.entity.Role;
import by.epam.likeit.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String SQL_SELECT_ALL_USERS = "SELECT login, password,name, email, role FROM users";
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT login, password, name, email, role FROM users WHERE login=?";
    private static final String SQL_INSERT_USER = "INSERT INTO users (login, password, name, email, role) VALUES(?,?,?,?,?)";

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String USERNAME = "name";
    private static final String EMAIL = "email";

    @Override
    public void create(User user) throws DaoException {
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getEmail());
            statement.setString(5, "user");
            statement.executeUpdate();
            connection.close();
        } catch (SQLException |ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User retrieve(String login) throws DaoException {
        User user = null;

        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement st = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            st.setString(1, login);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                user = new User(rs.getString(LOGIN), rs.getString(PASSWORD), rs.getString(USERNAME),
                        Role.valueOf(rs.getString(ROLE).toUpperCase()), rs.getString(EMAIL));
            }
            connection.close();
        } catch (SQLException | ConnectionPoolException e) {
            throw  new DaoException(e);
        }
        return user;
    }

    @Override
    public List<User> retrieveAll() throws DaoException {
        List<User>  users = new ArrayList<>();
        User user = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement st = connection.prepareStatement(SQL_SELECT_ALL_USERS);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                user = new User(rs.getString(LOGIN), rs.getString(PASSWORD), rs.getString(USERNAME),
                        Role.valueOf(rs.getString(ROLE).toUpperCase()), rs.getString(EMAIL));
                users.add(user);
            }
            connection.close();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }

        return users;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User retrieveUserByEmail(String email) {
        return null;
    }
}
