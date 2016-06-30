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

    private static final String SQL_INSERT_QUESTION = "INSERT INTO questions(topic_id, text, author) values(?, ?, ?)";
    private static final String SQL_SELECT_QUESTION_BY_ID="SELECT id, topic, text, author from questions LEFT JOIN topics using(topic_id) where id=?";
    private static final String SQL_SELECT_ALL_QUESTIONS = "SELECT id, topic, text, author from questions LEFT JOIN topics using(topic_id) order by date desc";
    private static final String SQL_SELECT_ALL_QUESTIONS_BY_TOPIC = "SELECT id, topic, text, author from questions LEFT JOIN topics using(topic_id) where topic=? order by date desc";
    private static final String SQL_SELECT_LAST_QUESTIONS = "SELECT id, topic, text, author, date from questions LEFT JOIN topics using(topic_id) order by date desc LIMIT 10";
    private static final String SQL_SELECT_LAST_QUESTIONS_BY_TOPIC = "SELECT id, topic, text, author, date from questions LEFT JOIN topics using(topic_id) where topic=? order by date desc LIMIT 10";
    private static final String SQL_SELECT_LAST_QUESTIONS_AFTER_DATE = "SELECT id, topic, text, author, date from questions LEFT JOIN topics using(topic_id) WHERE date<? order by date desc LIMIT 10";
    private static final String SQL_SELECT_LAST_QUESTIONS_BY_TOPIC_AFTER_DATE = "SELECT id, topic, text, author, date from questions LEFT JOIN topics using(topic_id) where topic=? AND date<? order by date desc LIMIT 10";
    private static final String SQL_DELETE_QUESTION = "DELETE FROM questions WHERE id=?";
    private static final String SQL_UPDATE_QUESTION = "UPDATE questions SET text=? WHERE id=?";
    private static final String SQL_SELECT_AUTHOR_OF_QUSTION = "SELECT author FROM questions WHERE id=?";

    private static final String ID = "id";
    private static final String TOPIC = "topic";
    private static final String TEXT = "text";
    private static final String AUTHOR = "author";
    private static final String DATE = "date";
    private static final String EMPTY = "";

    @Override
    public void create(Question entity) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_INSERT_QUESTION);
            ps.setInt(1, entity.getTopicId());
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
        Question question = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ResultSet rs = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_QUESTION_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                question = new Question();
                question.setId(rs.getInt(ID));
                question.setText(rs.getString(TEXT));
                question.setTopic(rs.getString(TOPIC));
                question.setAuthor(rs.getString(AUTHOR));
            }
        } catch (ConnectionPoolException | SQLException e) {
            new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps);
            }
        }
        return question;
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
            rs = st.executeQuery(SQL_SELECT_ALL_QUESTIONS);
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
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try{
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_UPDATE_QUESTION);
            ps.setString(1, entity.getText());
            ps.setInt(2, entity.getId());
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

    @Override
    public String getAuthorOfQuestion(int id) throws DaoException {
        String author = EMPTY;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_AUTHOR_OF_QUSTION);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                author = rs.getString(AUTHOR);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps, rs);
            }
        }
        return author;
    }

    @Override
    public List<Question> retrieveAll(String topic) throws DaoException {
        List<Question> questions = new ArrayList<>();
        Question question = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_ALL_QUESTIONS_BY_TOPIC);
            ps.setString(1, topic);
            rs = ps.executeQuery();
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
                pool.closeConnection(connection, ps, rs);
            }
        }
        return questions;
    }

    @Override
    public List<Question> retrieveLast() throws DaoException {
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
            rs = st.executeQuery(SQL_SELECT_LAST_QUESTIONS);
            while (rs.next()){
                question = new Question();
                question.setId(rs.getInt(ID));
                question.setText(rs.getString(TEXT));
                question.setAuthor(rs.getString(AUTHOR));
                question.setTopic(rs.getString(TOPIC));
                question.setDate(rs.getTimestamp(DATE).toString());
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
    public List<Question> retrieveLast(String topic) throws DaoException {
        List<Question> questions = new ArrayList<>();
        Question question = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_LAST_QUESTIONS_BY_TOPIC);
            ps.setString(1, topic);
            rs = ps.executeQuery();
            while (rs.next()){
                question = new Question();
                question.setId(rs.getInt(ID));
                question.setText(rs.getString(TEXT));
                question.setAuthor(rs.getString(AUTHOR));
                question.setTopic(rs.getString(TOPIC));
                question.setDate(rs.getTimestamp(DATE).toString());
                questions.add(question);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps, rs);
            }
        }
        return questions;
    }

    @Override
    public List<Question> retrieveNext(Timestamp lastDate) throws DaoException {
        List<Question> questions = new ArrayList<>();
        Question question = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_LAST_QUESTIONS_AFTER_DATE);
            ps.setTimestamp(1, lastDate);
            rs = ps.executeQuery();
            while (rs.next()){
                question = new Question();
                question.setId(rs.getInt(ID));
                question.setText(rs.getString(TEXT));
                question.setAuthor(rs.getString(AUTHOR));
                question.setTopic(rs.getString(TOPIC));
                question.setDate(rs.getTimestamp(DATE).toString());
                questions.add(question);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps, rs);
            }
        }
        return questions;
    }

    @Override
    public List<Question> retrieveNext(String topic, Timestamp lastDate) throws DaoException {
        List<Question> questions = new ArrayList<>();
        Question question = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_LAST_QUESTIONS_BY_TOPIC_AFTER_DATE);
            ps.setString(1, topic);
            ps.setTimestamp(2, lastDate);
            rs = ps.executeQuery();
            while (rs.next()){
                question = new Question();
                question.setId(rs.getInt(ID));
                question.setText(rs.getString(TEXT));
                question.setAuthor(rs.getString(AUTHOR));
                question.setTopic(rs.getString(TOPIC));
                question.setDate(rs.getTimestamp(DATE).toString());
                questions.add(question);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps, rs);
            }
        }
        return questions;
    }
}
