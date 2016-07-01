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

    private static final String SQL_SELECT_ALL_USERS = "SELECT login, password, name, last_name, email, role, url FROM users";
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT login, password, name, last_name, email, role, url FROM users WHERE login=?";
    private static final String SQL_SELECT_USER_BY_EMAIL = "SELECT login, password, name, last_name, email, role, url FROM users WHERE email=?";
    private static final String SQL_INSERT_USER = "INSERT INTO users (login, password, name, last_name, email, role) VALUES(?,?,?,?,?,?)";
    private static final String SQL_SELECT_SUM_OF_MARKS = "SELECT SUM(mark) as rating FROM users U JOIN answers A ON U.login=A.author JOIN marks M ON M.answer_id=A.id WHERE login=?";
    private static final String SQL_SELECT_COUNT_OF_MARKS = "SELECT COUNT(mark) as cnt FROM users U JOIN answers A ON U.login=A.author JOIN marks M ON M.answer_id=A.id WHERE login=?";
    private static final String SQL_SELECT_USER_PASSWORD = "SELECT password FROM users WHERE login=?";
    private static final String SQL_UPDATE_PASSWORD = "UPDATE users SET password=? WHERE login=?";
    private static final String SQL_UPDATE_IMAGE = "UPDATE users SET url=? WHERE login=?";
    private static final String SQL_UPDATE_USER = "UPDATE users SET name=?, last_name=?, email=? WHERE login=?";

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String USERNAME = "name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String RATING = "rating";
    private static final String COUNT = "cnt";
    private static final String URL = "url";
    private static final String USER = "user";
    private static final String EMPTY = "";
    private static final String ERROR_DUPLICATE = "prop.duplicate";

    public static final int MYSQL_DUPLICATE_PK = 1062;

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
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getEmail());
            ps.setString(6, USER);
            ps.executeUpdate();
        } catch (SQLException e) {
            if(e.getErrorCode() == MYSQL_DUPLICATE_PK){
                throw new DaoException(ERROR_DUPLICATE);
            } else{
                throw new DaoException(e);
            }
        } catch (ConnectionPoolException e){
            throw new DaoException(e);
        }
        finally {
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
                String lastName = rs.getString(LAST_NAME);
                String email = rs.getString(EMAIL);
                String imageURL = rs.getString(URL);
                Role role = Role.valueOf(rs.getString(ROLE).toUpperCase());

                user = new User(login, password, username, lastName, role, email, imageURL);
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
                String lastName = rs.getString(LAST_NAME);
                String email = rs.getString(EMAIL);
                String imageURL = rs.getString(URL);
                Role role = Role.valueOf(rs.getString(ROLE).toUpperCase());
                user = new User(login, password, username, lastName, role, email, imageURL);
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
    public void update(User entity) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_UPDATE_USER);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getLogin());
            ps.executeUpdate();
        } catch (SQLException e) {
            if(e.getErrorCode() == MYSQL_DUPLICATE_PK){
                throw new DaoException(ERROR_DUPLICATE);
            } else{
                throw new DaoException(e);
            }
        } catch (ConnectionPoolException e){
            throw new DaoException(e);
        }
        finally {
            if(connection != null){
                pool.closeConnection(connection, ps);
            }
        }
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User retrieveUserByEmail(String email) throws DaoException {
        User user = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next()){
                String login = rs.getString(LOGIN);
                String password = rs.getString(PASSWORD);
                String username = rs.getString(USERNAME);
                String lastName = rs.getString(LAST_NAME);
                String imageURL = rs.getString(URL);
                Role role = Role.valueOf(rs.getString(ROLE).toUpperCase());

                user = new User(login, password, username, lastName, role, email, imageURL);
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
    public String retrieveUserPassword(String login) throws DaoException {
        String password = EMPTY;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_USER_PASSWORD);
            ps.setString(1, login);
            rs = ps.executeQuery();
            while (rs.next()){
                password = rs.getString(PASSWORD);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps, rs);
            }
        }

        return password;
    }

    @Override
    public int getSumOfMarksByLogin(String login) throws DaoException {
        int sum = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_SUM_OF_MARKS);
            ps.setString(1, login);
            rs = ps.executeQuery();
            while (rs.next()){
                sum = rs.getInt(RATING);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps, rs);
            }
        }

        return sum;
    }

    @Override
    public int getCountOfMarksByLogin(String login) throws DaoException {
        int count = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_COUNT_OF_MARKS);
            ps.setString(1, login);
            rs = ps.executeQuery();
            while (rs.next()){
                count = rs.getInt(COUNT);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps, rs);
            }
        }
        return count;
    }

    @Override
    public void updatePassword(String login, String newPassword) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_UPDATE_PASSWORD);
            ps.setString(1, newPassword);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps);
            }
        }
    }

    @Override
    public void updateImage(String login, String url) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_UPDATE_IMAGE);
            ps.setString(1, url);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps);
            }
        }
    }
}
