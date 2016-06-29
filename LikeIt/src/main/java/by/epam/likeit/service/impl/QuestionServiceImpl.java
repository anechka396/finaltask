package by.epam.likeit.service.impl;

import by.epam.likeit.dao.QuestionDAO;
import by.epam.likeit.dao.QuestionDAOFactory;
import by.epam.likeit.dao.TopicDAO;
import by.epam.likeit.dao.TopicDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Question;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.QuestionService;
import by.epam.likeit.service.exception.ServiceException;

import java.sql.Timestamp;
import java.util.Date;

public class QuestionServiceImpl implements QuestionService {
    @Override
    public void addQuestion(User user, String topic, String text) throws ServiceException {
        try {
            TopicDAO topicDAO = TopicDAOFactory.getInstance();
            int topicId = topicDAO.retrieveIdByName(topic);
            Date dt = new Date();
            Question question = new Question(0, text, user.getLogin(), topic, topicId, new Timestamp(dt.getTime()));
            QuestionDAO questionDAO = QuestionDAOFactory.getInstance();
            questionDAO.create(question);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void editQuestion(int id, String value) throws ServiceException {
        try {
            Question question = new Question();
            question.setId(id);
            question.setText(value);

            QuestionDAO questionDAO = QuestionDAOFactory.getInstance();
            questionDAO.update(question);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
