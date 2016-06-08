package by.epam.likeit.dao;

import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Question;

import java.sql.Timestamp;
import java.util.List;

public interface QuestionDAO extends BaseDAO<Question, Integer> {
    public List<Question> retrieveAll(String topic) throws DaoException;
}
