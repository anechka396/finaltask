package by.epam.likeit.dao;

import by.epam.likeit.dao.impl.TopicDAOImpl;

public class TopicDAOFactory {
    private static TopicDAO topicDAO = new TopicDAOImpl();

    public static TopicDAO getInstance(){
        return topicDAO;
    }
}
