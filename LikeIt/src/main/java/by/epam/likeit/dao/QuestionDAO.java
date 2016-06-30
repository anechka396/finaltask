package by.epam.likeit.dao;

import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Question;

import java.sql.Timestamp;
import java.util.List;

public interface QuestionDAO extends BaseDAO<Question, Integer> {
    List<Question> retrieveAll(String topic) throws DaoException;
    List<Question> retrieveLast() throws DaoException;
    List<Question> retrieveLast(String topic) throws DaoException;
    List<Question> retrieveNext(Timestamp lastDate) throws DaoException;
    List<Question> retrieveNext(String topic, Timestamp lastDate) throws DaoException;
}
