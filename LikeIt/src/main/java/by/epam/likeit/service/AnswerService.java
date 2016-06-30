package by.epam.likeit.service;

import by.epam.likeit.entity.User;
import by.epam.likeit.service.exception.ServiceException;

public interface AnswerService {
    void addAnswer(int id, String text, User user) throws ServiceException;
    void deleteAnswer(int id, User user) throws ServiceException;
    void setRating(int id, User user, int mark) throws ServiceException;
}
