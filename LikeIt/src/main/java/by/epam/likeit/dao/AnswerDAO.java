package by.epam.likeit.dao;

import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Answer;

import java.util.List;

/**
 * Created by Пользователь on 04.05.2016.
 */
public interface AnswerDAO extends BaseDAO<Answer,Integer> {
    public List<Answer> retrieveAllByQuestionId(int id) throws DaoException;
}
