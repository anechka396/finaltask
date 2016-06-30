package by.epam.likeit.service.impl;

import by.epam.likeit.dao.QuestionDAO;
import by.epam.likeit.dao.QuestionDAOFactory;
import by.epam.likeit.dao.TopicDAO;
import by.epam.likeit.dao.TopicDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Question;
import by.epam.likeit.entity.Role;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.QuestionService;
import by.epam.likeit.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.Date;

public class QuestionServiceImpl implements QuestionService {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private void validateUser(User user) throws ServiceException {
        if(user == null){
            throw new ServiceException();
        }
    }

    @Override
    public void addQuestion(User user, String topic, String text) throws ServiceException {
        validateUser(user);

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
    public void editQuestion(User user, int id, String value) throws ServiceException {
        validateUser(user);
        try {

            Question question = new Question();
            question.setId(id);
            question.setText(value);

            QuestionDAO questionDAO = QuestionDAOFactory.getInstance();
            String author = questionDAO.getAuthorOfQuestion(id);

            if(!author.equals(user.getLogin())){
                throw new ServiceException();
            }
            questionDAO.update(question);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteQuestion(User user, int id) throws ServiceException {
        validateUser(user);
        QuestionDAO questionDAO = QuestionDAOFactory.getInstance();
        try {
            String author = questionDAO.getAuthorOfQuestion(id);
            if(!author.equals(user.getLogin()) && !user.getRole().equals(Role.ADMIN)){
                throw new ServiceException();
            }
            questionDAO.delete(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }

    }
}
