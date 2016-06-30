package by.epam.likeit.dao;

import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Answer;

import java.util.List;

/**
 * Created by Пользователь on 04.05.2016.
 */
public interface AnswerDAO extends BaseDAO<Answer,Integer> {
    List<Answer> retrieveAllByQuestionId(int id) throws DaoException;
    List<Answer> retrieveAllWithMarksByQuestionId(int id, String user) throws DaoException;
    void setRatingToAnswer(int id, String author, int mark) throws DaoException;
    String getAuthorOfAnswer(int id) throws DaoException;
}
