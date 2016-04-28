package by.epam.likeit.dao.impl;

import by.epam.likeit.dao.QuestionDAO;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.dao.pool.ConnectionPool;
import by.epam.likeit.dao.pool.exception.ConnectionPoolException;
import by.epam.likeit.entity.Question;
import by.epam.likeit.entity.Role;
import by.epam.likeit.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Пользователь on 28.04.2016.
 */
public class QuestionDAOImpl implements QuestionDAO {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String SQL_INSERT_QUESTION = "INSERT INTO questions(topic, text, author) values(?, ?, ?)";
    private static final String SQL_SELECT_ALL_QUSTIONS = "SELECT id, topic, text, author from questions order by date desc";

    private static final String ID = "id";
    private static final String TOPIC = "topic";
    private static final String TEXT = "text";
    private static final String AUTHOR = "author";

    @Override
    public void create(Question entity) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_QUESTION);
            ps.setString(1, entity.getTopic());
            ps.setString(2, entity.getText());
            ps.setString(3, entity.getAuthor());
            ps.executeUpdate();
            connection.close();
        } catch (ConnectionPoolException e) {
            new DaoException(e);
        } catch (SQLException e) {
            new DaoException(e);
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
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement st = connection.prepareStatement(SQL_SELECT_ALL_QUSTIONS);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                question = new Question();
                question.setId(rs.getInt(ID));
                question.setText(rs.getString(TEXT));
                question.setAuthor(rs.getString(AUTHOR));
                question.setTopic(rs.getString(TOPIC));
                questions.add(question);
            }
            connection.close();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
        return questions;
    }

    @Override
    public void update(Question entity) {

    }

    @Override
    public void delete(Integer id) {

    }
}
