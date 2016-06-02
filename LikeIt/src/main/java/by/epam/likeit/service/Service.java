package by.epam.likeit.service;

import by.epam.likeit.entity.Answer;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.exception.ServiceException;

import java.util.List;

public abstract class Service {

    public void service(int id, String text, String author) throws ServiceException {}

    public User service(String login, String password) throws ServiceException {
        return null;
    }

    public User service(String login, String password, String name, String email, String role) throws ServiceException {
        return null;
    }

    public void service(User user, String topic, String text) throws ServiceException {}

    public void service(int id, String value) throws ServiceException {}

    public void service(int id, User user, int mark) throws ServiceException {}
}
