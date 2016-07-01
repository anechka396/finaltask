package by.epam.likeit.dao;

import by.epam.likeit.entity.Topic;

/**
 * Extends BaseDAO interface with special operations.
 * E is Topic entity.
 * K is Integer value.
 * @author Anna Yakubenko
 * @author anechka396@mail.ru
 * @version 1.0
 */
public interface TopicDAO extends BaseDAO<Topic, Integer> {
    /**
     * Returns the id of topic by the name.
     * @param topic Name of the topic.
     * @return Id of the topic.
     */
    int retrieveIdByName(String topic);
}
