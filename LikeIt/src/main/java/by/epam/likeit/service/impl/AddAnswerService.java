package by.epam.likeit.service.impl;

import by.epam.likeit.dao.AnswerDAO;
import by.epam.likeit.dao.AnswerDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Answer;
import by.epam.likeit.service.Service;
import by.epam.likeit.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Пользователь on 04.05.2016.
 */
public class AddAnswerService extends Service {
    @Override
    public void service(int id, String text, String author) throws ServiceException {
      //  List<Answer> answers = null;
        try {
            Answer answer = new Answer();
            answer.setQId(id);
            answer.setText(text);
            answer.setAuthor(author);

            AnswerDAO answerDAO = AnswerDAOFactory.getInstance();
            answerDAO.create(answer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
