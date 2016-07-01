package by.epam.likeit.dao;

import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Question;

import java.sql.Timestamp;
import java.util.List;

/**
 * Extends BaseDAO interface with special operations.
 * E is Question entity.
 * K is Integer value.
 * @author Anna Yakubenko
 * @author anechka396@mail.ru
 * @version 1.0
 */
public interface QuestionDAO extends BaseDAO<Question, Integer> {
    /**
     * Returns the author of the question by id.
     * @param id Identifier of the question.
     * @return Author of the question
     * @throws DaoException
     */
    String getAuthorOfQuestion(int id) throws DaoException;

    /**
     * Retrieve all questions with topic name 'topic'.
     * @param topic The name of topic
     * @return List of questions
     * @throws DaoException
     */
    List<Question> retrieveAll(String topic) throws DaoException;

    /**
     * Retrieve the last 10 questions.
     * @return List of questions
     * @throws DaoException
     */
    List<Question> retrieveLast() throws DaoException;

    /**
     * Retrieve the last 10 questions with topic name 'topic'.
     * @param topic The name of topic
     * @return List of questions
     * @throws DaoException
     */
    List<Question> retrieveLast(String topic) throws DaoException;

    /**
     * Retrieve 10 questions before date 'lastDate'.
     * @param lastDate
     * @return List of questions
     * @throws DaoException
     */
    List<Question> retrieveNext(Timestamp lastDate) throws DaoException;

    /**
     * Retrieve 10 questions before date 'lastDate' with topic name 'topic'.
     * @param lastDate
     * @param topic The name of topic.
     * @return List of questions
     * @throws DaoException
     */
    List<Question> retrieveNext(String topic, Timestamp lastDate) throws DaoException;
}
