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
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_INSERT_USER);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getEmail());
            ps.setString(5, "user");
            ps.executeUpdate();
        } catch (SQLException |ConnectionPoolException e) {
            throw new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps);
            }
        }
    }

    @Override
    public User retrieve(String login) throws DaoException {
        User user = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            ps.setString(1, login);
            rs = ps.executeQuery();

            while (rs.next()){
                String password = rs.getString(PASSWORD);
                String username = rs.getString(USERNAME);
                String email = rs.getString(EMAIL);
                Role role = Role.valueOf(rs.getString(ROLE).toUpperCase());

                user = new User(login, password, username, role, email);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw  new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps, rs);
            }
        }

        return user;
    }

    @Override
    public List<User> retrieveAll() throws DaoException {
        List<User>  users = new ArrayList<>();
        User user = null;
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            st = connection.createStatement();
            rs = st.executeQuery(SQL_SELECT_ALL_USERS);
            while (rs.next()){
                String login = rs.getString(LOGIN);
                String password = rs.getString(PASSWORD);
                String username = rs.getString(USERNAME);
                String email = rs.getString(EMAIL);
                Role role = Role.valueOf(rs.getString(ROLE).toUpperCase());
                user = new User(login, password, username, role, email);
                users.add(user);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, st, rs);
            }
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
