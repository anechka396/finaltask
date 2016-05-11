package by.epam.likeit.service.impl;

import by.epam.likeit.dao.AnswerDAO;
import by.epam.likeit.dao.AnswerDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.AbstractService;
import by.epam.likeit.service.exception.ServiceException;

/**
 * Created by Пользователь on 08.05.2016.
 */
public class SetRatingService extends AbstractService<User> {
    @Override
    public void service(int id, User user, int mark) throws ServiceException {
        AnswerDAO answerDAO = AnswerDAOFactory.getInstance();
        try {
            answerDAO.setRatingToAnswer(id, user.getLogin(), mark);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
