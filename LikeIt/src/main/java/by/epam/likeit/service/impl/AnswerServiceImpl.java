package by.epam.likeit.service.impl;

import by.epam.likeit.dao.AnswerDAO;
import by.epam.likeit.dao.AnswerDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Answer;
import by.epam.likeit.entity.Role;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.AnswerService;
import by.epam.likeit.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnswerServiceImpl implements AnswerService {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private void validateUser(User user) throws ServiceException {
        if(user == null){
            throw new ServiceException();
        }
    }

    @Override
    public void addAnswer(int id, String text, User user) throws ServiceException {
        validateUser(user);

        try {
            Answer answer = new Answer();
            answer.setQId(id);
            answer.setText(text);
            answer.setAuthor(user.getLogin());

            AnswerDAO answerDAO = AnswerDAOFactory.getInstance();
            answerDAO.create(answer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteAnswer(int id, User user) throws ServiceException {
        validateUser(user);

        AnswerDAO answerDAO = AnswerDAOFactory.getInstance();
        try {
            String author = answerDAO.getAuthorOfAnswer(id);
            LOGGER.trace(author);
            LOGGER.trace(user.getLogin());
            if(!author.equals(user.getLogin()) && !user.getRole().equals(Role.ADMIN)){
                throw new ServiceException();
            }
            answerDAO.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public void setRating(int id, User user, int mark) throws ServiceException {
        validateUser(user);

        try {
            AnswerDAO answerDAO = AnswerDAOFactory.getInstance();
            String author = answerDAO.getAuthorOfAnswer(id);
            if(author.equals(user.getLogin())){
                throw new ServiceException();
            }
            answerDAO.setRatingToAnswer(id, user.getLogin(), mark);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
