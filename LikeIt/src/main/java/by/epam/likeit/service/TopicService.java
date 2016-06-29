package by.epam.likeit.service;

import by.epam.likeit.entity.User;
import by.epam.likeit.service.exception.ServiceException;

public interface TopicService {
    void addTopic(User user, String value) throws ServiceException;
    void editTopic(int id, String value) throws ServiceException;
    void deleteTopic(int id) throws ServiceException;
}
