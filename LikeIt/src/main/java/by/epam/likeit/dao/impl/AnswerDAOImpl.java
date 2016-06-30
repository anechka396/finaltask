package by.epam.likeit.dao.impl;

import by.epam.likeit.dao.AnswerDAO;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.dao.pool.ConnectionPool;
import by.epam.likeit.dao.pool.exception.ConnectionPoolException;
import by.epam.likeit.entity.Answer;
import by.epam.likeit.entity.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAOImpl implements AnswerDAO {

    private static final String SQL_SELECT_ANSWERS_BY_Q_ID = "SELECT id, text, author from answers WHERE question_id=? ORDER BY date ";
    private static final String SQL_SELECT_ANSWERS_WITH_MARKS_BY_Q_ID = "SELECT id, text, author, mark from answers a LEFT JOIN (SELECT * FROM marks WHERE user=?) m ON a.id=m.answer_id WHERE question_id=? ORDER BY date";
    private static final String SQL_INSERT_ANSWER = "INSERT INTO answers(text, question_id, author) VALUES(?, ?, ?)";
    private static final String SQL_DELETE_ANSWER = "DELETE FROM answers WHERE id=?";
    private static final String SQL_SELECT_AUTHOR_OF_ANSWER = "SELECT author FROM answers WHERE id=?";
    private static final String SQL_REPLACE_ANSWER = "REPLACE INTO marks(answer_id, user, mark) VALUES(?,?,?)";

    private static final String ID = "id";
    private static final String TEXT = "text";
    private static final String AUTHOR = "author";
    private static final String MARK = "mark";
    private static final String EMPTY = "";

    @Override
    public void create(Answer entity) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_INSERT_ANSWER);
            ps.setString(1, entity.getText());
            ps.setInt(2, entity.getQId());
            ps.setString(3, entity.getAuthor());
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
    public Answer retrieve(Integer id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Answer> retrieveAll() throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Answer entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_DELETE_ANSWER);
            ps.setInt(1, id);
            ps.execute();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps);
            }
        }
    }

    @Override
    public List<Answer> retrieveAllByQuestionId(int id) throws DaoException {
        List<Answer> answers = new ArrayList<>();
        Answer answer = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_ANSWERS_BY_Q_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                answer = new Answer();
                answer.setId(rs.getInt(ID));
                answer.setText(rs.getString(TEXT));
                answer.setAuthor(rs.getString(AUTHOR));
                answers.add(answer);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps, rs);
            }
        }
        return answers;
    }

    @Override
    public List<Answer> retrieveAllWithMarksByQuestionId(int id, String user) throws DaoException {
        List<Answer> answers = new ArrayList<>();
        Answer answer = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_ANSWERS_WITH_MARKS_BY_Q_ID);
            ps.setInt(2, id);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()){
                answer = new Answer();
                answer.setId(rs.getInt(ID));
                answer.setText(rs.getString(TEXT));
                answer.setAuthor(rs.getString(AUTHOR));
                answer.setMark(rs.getInt(MARK));
                answers.add(answer);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            if(connection != null){
                pool.closeConnection(connection, ps, rs);
            }
        }
        return answers;
    }

    @Override
    public void setRatingToAnswer(int id, String user,int mark) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_REPLACE_ANSWER);
            ps.setInt(1, id);
            ps.setString(2, user);
            ps.setInt(3, mark);
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
    public String getAuthorOfAnswer(int id) throws DaoException {
        String author = EMPTY;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_AUTHOR_OF_ANSWER);
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
}
