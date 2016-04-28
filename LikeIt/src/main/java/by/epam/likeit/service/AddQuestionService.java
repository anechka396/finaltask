package by.epam.likeit.service;

import by.epam.likeit.dao.QuestionDAO;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.dao.impl.QuestionDAOImpl;
import by.epam.likeit.entity.Question;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.exception.ServiceException;

/**
 * Created by Пользователь on 28.04.2016.
 */
public class AddQuestionService {
    private static final QuestionDAO dao = new QuestionDAOImpl();

    public void service(User user, String topic, String text) throws ServiceException {
        Question question = new Question(0, text, user.getLogin(), topic);
        try {
            dao.create(question);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
