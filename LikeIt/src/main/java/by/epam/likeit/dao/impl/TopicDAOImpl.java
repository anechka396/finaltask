package by.epam.likeit.dao.impl;

import by.epam.likeit.dao.TopicDAO;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.dao.pool.ConnectionPool;
import by.epam.likeit.dao.pool.exception.ConnectionPoolException;
import by.epam.likeit.entity.Topic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicDAOImpl implements TopicDAO {

    private static final String SQL_CREATE_NEW_TOPIC = "INSERT INTO topics(topic) values(?)";
    private static final String SQL_SELECT_ALL_TOPICS = "SELECT topic_id, topic FROM topics";
    private static final String SQL_SELECT_ID_BY_NAME = "SELECT topic_id FROM topics WHERE topic=?";
    private static final String SQL_UPDATE_TOPIC = "UPDATE topics SET topic=? WHERE topic_id=?";
    private static final String SQL_DELETE_TOPIC = "DELETE FROM topics WHERE topic_id=?";

    private static final String TOPIC = "topic";
    private static final String ID = "topic_id";

    @Override
    public void create(Topic entity) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_CREATE_NEW_TOPIC);
            ps.setString(1, entity.getValue());
            ps.executeUpdate();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Topic retrieve(Integer id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Topic> retrieveAll() throws DaoException {
        List<Topic> topics = new ArrayList<>();
        Topic topic = null;
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
                topic = new Topic();
                topic.setValue(rs.getString(TOPIC));
                topic.setId(rs.getInt(ID));
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
    public void update(Topic entity) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_UPDATE_TOPIC);
            ps.setString(1, entity.getValue());
            ps.setInt(2, entity.getId());
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
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_DELETE_TOPIC);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps);
            }
        }
    }

    @Override
    public int retrieveIdByName(String topic) {
        int id = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConnectionPool pool = null;
        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_ID_BY_NAME);
            ps.setString(1, topic);
            rs = ps.executeQuery();
            while(rs.next()){
               id = rs.getInt(ID);
            }
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                pool.closeConnection(connection, ps, rs);
            }
        }

        return id;
    }
}
