package by.epam.likeit.service.impl;

import by.epam.likeit.dao.AnswerDAO;
import by.epam.likeit.dao.AnswerDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Answer;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.AnswerService;
import by.epam.likeit.service.exception.ServiceException;

public class AnswerServiceImpl implements AnswerService {
    @Override
    public void addAnswer(int id, String text, String author) throws ServiceException {
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

    @Override
    public void setRating(int id, User user, int mark) throws ServiceException {
        try {
            AnswerDAO answerDAO = AnswerDAOFactory.getInstance();
            answerDAO.setRatingToAnswer(id, user.getLogin(), mark);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
