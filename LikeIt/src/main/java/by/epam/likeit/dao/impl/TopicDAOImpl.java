package by.epam.likeit.dao.impl;

import by.epam.likeit.dao.TopicDAO;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.dao.pool.ConnectionPool;
import by.epam.likeit.dao.pool.exception.ConnectionPoolException;
import by.epam.likeit.entity.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Пользователь on 29.04.2016.
 */
public class TopicDAOImpl implements TopicDAO {

    private static final String SQL_SELECT_ALL_TOPICS = "SELECT name FROM topics";

    @Override
    public void create(String entity) throws DaoException {

    }

    @Override
    public String retrieve(String id) throws DaoException {
        return null;
    }

    @Override
    public List<String> retrieveAll() throws DaoException {
        List<String> topics = new ArrayList<>();
        String topic = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement st = connection.prepareStatement(SQL_SELECT_ALL_TOPICS);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                topic = rs.getString("name");
                topics.add(topic);
            }
            connection.close();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
        return topics;
    }

    @Override
    public void update(String entity) {

    }

    @Override
    public void delete(String id) {

    }
}
