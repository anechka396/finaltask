package by.epam.likeit.service.impl;

import by.epam.likeit.dao.QuestionDAO;
import by.epam.likeit.dao.QuestionDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Question;
import by.epam.likeit.service.AbstractService;
import by.epam.likeit.service.exception.ServiceException;

/**
 * Created by Пользователь on 14.05.2016.
 */
public class EditQuestionService extends AbstractService<Question> {
    public void service(int id, String value) throws ServiceException{
        Question question = new Question();
        question.setId(id);
        question.setText(value);
        QuestionDAO questionDAO = QuestionDAOFactory.getInstance();
        try {
            questionDAO.update(question);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
