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
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

    @Override
    public void create(User user) {
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO users (login, password, username, email, role) VALUES ('"+user.getLogin()+"', '" +
            user.getPassword() +"', '"+ user.getName()+"', '" + user.getEmail() +"', 'user')");
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User retrieve(String login) {
        User user = null;

        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users WHERE login='" + login +
                    "'");

            while (rs.next()){
                user = new User(rs.getString("login"), rs.getString("password"),
                        rs.getString("username"), Role.valueOf(rs.getString("role").toUpperCase()),
                        rs.getString("email"));
            }
        } catch (SQLException e) {
          //  throw  new DaoException("", e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> retriveAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(String id) {

    }
}
