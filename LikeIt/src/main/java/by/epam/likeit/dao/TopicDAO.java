package by.epam.likeit.dao;

import by.epam.likeit.entity.Topic;

public interface TopicDAO extends BaseDAO<Topic,String> {
    public int retrieveIdByName(String topic);
}
