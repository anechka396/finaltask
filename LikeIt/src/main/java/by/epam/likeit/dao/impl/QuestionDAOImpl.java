package by.epam.likeit.dao.impl;

import by.epam.likeit.dao.QuestionDAO;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.dao.pool.ConnectionPool;
import by.epam.likeit.dao.pool.exception.ConnectionPoolException;
import by.epam.likeit.entity.Question;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAOImpl implements QuestionDAO {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String SQL_INSERT_QUESTION = "INSERT INTO questions(topic, text, author) values(?, ?, ?)";
    private static final String SQL_SELECT_ALL_QUSTIONS = "SELECT id, topic, text, author from questions order by date desc";
    private static final String SQL_DELETE_QUESTION = "DELETE FROM questions WHERE id=?";

    private static final String ID = "id";
    private static final String TOPIC = "topic";
    private static final String TEXT = "text";
    private static final String AUTHOR = "author";

    @Override
    public void create(Question entity) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_INSERT_QUESTION);
            ps.setString(1, entity.getTopic());
            ps.setString(2, entity.getText());
            ps.setString(3, entity.getAuthor());
            ps.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps);
            }
        }
    }

    @Override
    public Question retrieve(Integer id) throws DaoException {
        return null;
    }

    @Override
    public List<Question> retrieveAll() throws DaoException {

        List<Question> questions = new ArrayList<>();
        Question question = null;
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            st = connection.createStatement();
            rs = st.executeQuery(SQL_SELECT_ALL_QUSTIONS);
            while (rs.next()){
                question = new Question();
                question.setId(rs.getInt(ID));
                question.setText(rs.getString(TEXT));
                question.setAuthor(rs.getString(AUTHOR));
                question.setTopic(rs.getString(TOPIC));
                questions.add(question);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, st, rs);
            }
        }
        return questions;
    }

    @Override
    public void update(Question entity) {

    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_DELETE_QUESTION);
            ps.setInt(1, id);
            ps.execute();
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps);
            }
        }
    }
}
