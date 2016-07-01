package by.epam.likeit.dao;

import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Answer;

import java.util.List;

/**
 * Extends BaseDAO interface with special operations.
 * E is Answer entity.
 * K is Integer value.
 * @author Anna Yakubenko
 * @author anechka396@mail.ru
 * @version 1.0
 */
public interface AnswerDAO extends BaseDAO<Answer,Integer> {
    /**
     * Allows retrieve all answers to question with identifier id.
     * @param id Identifier of question
     * @return The list of answers.
     * @throws DaoException
     */
    List<Answer> retrieveAllByQuestionId(int id) throws DaoException;

    /**
     * Allows retrieve all answers with marks to question with identifier id.
     * @param id Identifier of question.
     * @param user User who set the marks for answers.
     * @return The list of answers.
     * @throws DaoException
     */
    List<Answer> retrieveAllWithMarksByQuestionId(int id, String user) throws DaoException;

    /**
     * Set the mark to the answer with identifier id.
     * @param id Identifier of answer.
     * @param author User who set the mark
     * @param mark Mark value.
     * @throws DaoException
     */
    void setRatingToAnswer(int id, String author, int mark) throws DaoException;

    /**
     * Returns the author of answer by answer id.
     * @param id Identifier of answer.
     * @return Login of the answer author.
     * @throws DaoException
     */
    String getAuthorOfAnswer(int id) throws DaoException;
}
