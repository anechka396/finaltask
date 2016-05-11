package by.epam.likeit.service.impl;

import by.epam.likeit.dao.AnswerDAO;
import by.epam.likeit.dao.AnswerDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Answer;
import by.epam.likeit.service.AbstractService;
import by.epam.likeit.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Пользователь on 04.05.2016.
 */
public class AddAnswerService extends AbstractService<Answer> {
    @Override
    public List<Answer> service(int id, String text, String author) throws ServiceException {
        Answer answer = new Answer();
        List<Answer> answers = new ArrayList<>();
        answer.setQId(id);
        answer.setText(text);
        answer.setAuthor(author);
        AnswerDAO answerDAO = AnswerDAOFactory.getInstance();
        try {
            answerDAO.create(answer);
            answers = answerDAO.retrieveAllByQuestionId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return answers;
    }
}
