package by.epam.likeit.dao;

import by.epam.likeit.dao.impl.TopicDAOImpl;

/**
 * Returns the realization of class TopicDAO.
 * @author Anna Yakubenko
 * @author anechka396@mail.ru
 * @version 1.0
 */
public class TopicDAOFactory {
    private static TopicDAO topicDAO = new TopicDAOImpl();

    public static TopicDAO getInstance(){
        return topicDAO;
    }
}
