package by.epam.likeit.service;

import by.epam.likeit.entity.User;
import by.epam.likeit.service.exception.ServiceException;

public interface TopicService {
    /**
     * Add new topic.
     * @param user User who add topic.
     * @param value Topic name.
     * @throws ServiceException
     */
    void addTopic(User user, String value) throws ServiceException;

    /**
     * Edit topic.
     * @param user User who edit topic
     * @param id Id of the topic
     * @param value New value of the topic.
     * @throws ServiceException
     */
    void editTopic(User user, int id, String value) throws ServiceException;

    /**
     * Delete topic
     * @param user User who delete topic
     * @param id Id of the topic
     * @throws ServiceException
     */
    void deleteTopic(User user, int id) throws ServiceException;
}
