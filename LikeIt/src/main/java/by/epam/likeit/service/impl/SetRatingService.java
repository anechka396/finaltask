package by.epam.likeit.service.impl;

import by.epam.likeit.dao.AnswerDAO;
import by.epam.likeit.dao.AnswerDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.Service;
import by.epam.likeit.service.exception.ServiceException;

public class SetRatingService extends Service {

    @Override
    public void service(int id, User user, int mark) throws ServiceException {
        try {
            AnswerDAO answerDAO = AnswerDAOFactory.getInstance();
            answerDAO.setRatingToAnswer(id, user.getLogin(), mark);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
