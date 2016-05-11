package by.epam.likeit.service.impl;

import by.epam.likeit.dao.QuestionDAO;
import by.epam.likeit.dao.QuestionDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Question;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.AbstractService;
import by.epam.likeit.service.exception.ServiceException;

/**
 * Created by Пользователь on 28.04.2016.
 */
public class AddQuestionService extends AbstractService<User> {

    @Override
    public void service(User user, String topic, String text) throws ServiceException {
        Question question = new Question(0, text, user.getLogin(), topic);
        try {
            QuestionDAO questionDAO = QuestionDAOFactory.getInstance();
            questionDAO.create(question);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
