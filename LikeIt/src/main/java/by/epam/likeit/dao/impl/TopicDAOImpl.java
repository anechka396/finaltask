package by.epam.likeit.dao.impl;

import by.epam.likeit.dao.TopicDAO;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.dao.pool.ConnectionPool;
import by.epam.likeit.dao.pool.exception.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicDAOImpl implements TopicDAO {

    private static final String SQL_SELECT_ALL_TOPICS = "SELECT name FROM topics";

    private static final String NAME = "name";

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
        Statement st = null;
        ResultSet rs = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            st = connection.createStatement();
            rs = st.executeQuery(SQL_SELECT_ALL_TOPICS);
            while (rs.next()){
                topic = rs.getString(NAME);
                topics.add(topic);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, st, rs);
            }
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
