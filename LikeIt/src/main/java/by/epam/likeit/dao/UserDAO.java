package by.epam.likeit.dao;

import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.User;

import java.util.List;

/**
 * Extends BaseDAO interface with special operations.
 * E is User entity.
 * K is String value (login of the user).
 * @author Anna Yakubenko
 * @author anechka396@mail.ru
 * @version 1.0
 */
public interface UserDAO extends BaseDAO<User, String> {
    /**
     * Returns user by email.
     * @param email Email of the user
     * @return User
     * @throws DaoException
     */
    User retrieveUserByEmail(String email) throws DaoException;

    /**
     * Returns password of user with login 'login'.
     * @param login Login of the user.
     * @return Password of the user.
     * @throws DaoException
     */
    String retrieveUserPassword(String login) throws DaoException;

    /**
     * Returns sum of marks for user with login 'login'.
     * @param login Login of the user.
     * @return Sum of marks.
     * @throws DaoException
     */
    int getSumOfMarksByLogin(String login) throws DaoException;

    /**
     * Returns sum of marks for user with login 'login'.
     * @param login Login of the user.
     * @return Count of marks.
     * @throws DaoException
     */
    int getCountOfMarksByLogin(String login) throws DaoException;

    /**
     * Update password for user with login 'login'.
     * @param login Login of the user.
     * @param newPassword New password.
     * @throws DaoException
     */
    void updatePassword(String login, String newPassword) throws DaoException;

    /**
     * Update the image  for user with login 'login'.
     * @param login Login of the user.
     * @param url Url for new image.
     * @throws DaoException
     */
    void updateImage(String login, String url) throws DaoException;
}
