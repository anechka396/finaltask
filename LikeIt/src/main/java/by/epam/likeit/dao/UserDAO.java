package by.epam.likeit.dao;

import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.User;

import java.util.List;

public interface UserDAO extends BaseDAO<User, String> {
    User retrieveUserByEmail(String email);
    int getSumOfMarksByLogin(String login) throws DaoException;
    int getCountOfMarksByLogin(String login) throws DaoException;
}
