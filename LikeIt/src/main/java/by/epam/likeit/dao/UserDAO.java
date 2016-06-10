package by.epam.likeit.dao;

import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.User;

import java.util.List;

public interface UserDAO extends BaseDAO<User, String> {
    User retrieveUserByEmail(String email) throws DaoException;
    String retrieveUserPassword(String login) throws DaoException;
    int getSumOfMarksByLogin(String login) throws DaoException;
    int getCountOfMarksByLogin(String login) throws DaoException;
    void updatePassword(String login, String newPassword) throws DaoException;
    void updateImage(String login, String url) throws DaoException;
}
