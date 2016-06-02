package by.epam.likeit.service.impl;

import by.epam.likeit.dao.QuestionDAO;
import by.epam.likeit.dao.QuestionDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Question;
import by.epam.likeit.service.Service;
import by.epam.likeit.service.exception.ServiceException;

public class EditQuestionService extends Service {

    @Override
    public void service(int id, String value) throws ServiceException{
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
