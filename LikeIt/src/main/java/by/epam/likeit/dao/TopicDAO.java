package by.epam.likeit.dao;

public interface TopicDAO extends BaseDAO<String,String> {
    public int retrieveIdByName(String topic);
}
