package by.epam.likeit.service.impl;

import by.epam.likeit.dao.AnswerDAO;
import by.epam.likeit.dao.AnswerDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Answer;
import by.epam.likeit.service.AbsractService;
import by.epam.likeit.service.exception.ServiceException;

/**
 * Created by Пользователь on 04.05.2016.
 */
public class AddAnswerService extends AbsractService<Answer> {
    @Override
    public void service(int id, String text, String author) throws ServiceException {
        Answer answer = new Answer();
        answer.setQId(id);
        answer.setText(text);
        answer.setAuthor(author);
        AnswerDAO answerDAO = AnswerDAOFactory.getInstance();
        try {
            answerDAO.create(answer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
