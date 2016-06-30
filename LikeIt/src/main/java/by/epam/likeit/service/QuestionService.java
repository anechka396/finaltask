package by.epam.likeit.service;

import by.epam.likeit.entity.User;
import by.epam.likeit.service.exception.ServiceException;

public interface QuestionService {
    void addQuestion(User user, String topic, String text) throws ServiceException;
    void editQuestion(User user, int id, String value) throws ServiceException;
    void deleteQuestion(User user, int id) throws ServiceException;
}
