package by.epam.likeit.service.impl;

import by.epam.likeit.dao.TopicDAO;
import by.epam.likeit.dao.TopicDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Role;
import by.epam.likeit.entity.Topic;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.TopicService;
import by.epam.likeit.service.exception.ServiceException;

public class TopicServiceImpl implements TopicService {
    @Override
    public void addTopic(User user, String value) throws ServiceException {
        if(!user.getRole().equals(Role.ADMIN)){
            throw new ServiceException("");
        }

        Topic topic = new Topic();
        topic.setValue(value);

        TopicDAO topicDAO = TopicDAOFactory.getInstance();
        try {
            topicDAO.create(topic);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void editTopic(int id, String value) throws ServiceException {
        try {
            Topic topic = new Topic();
            topic.setId(id);
            topic.setValue(value);

            TopicDAO topicDAO = TopicDAOFactory.getInstance();
            topicDAO.update(topic);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteTopic(int id) throws ServiceException {

    }
}
