package by.epam.likeit.service;

import by.epam.likeit.entity.User;
import by.epam.likeit.service.exception.ServiceException;

import java.io.File;

public interface  UserService {
    /**
     * Login exist user.
     * @param login Login of the user.
     * @param password Password of the user.
     * @return User.
     * @throws ServiceException
     */
    User loginUser(String login, String password) throws ServiceException;

    /**
     * Add new user.
     * @param login Login of the user(unique).
     * @param password Password of the user.
     * @param repeatPassword Repeat password.
     * @param name Name of the user.
     * @param surname Surname of the user.
     * @param email Email of the user(unique).
     * @param role Role of the user(user or admin).
     * @return User
     * @throws ServiceException
     */
    User registerUser(String login, String password, String repeatPassword, String name, String surname, String email, String role) throws ServiceException;

    /**
     * Change password for the user 'user'.
     * @param user
     * @param oldPassword Old password.
     * @param newPassword New password.
     * @param repeatNewPassword Repeat new password.
     * @throws ServiceException
     */
    void changePassword(User user, String oldPassword, String newPassword, String repeatNewPassword) throws ServiceException;

    /**
     * Change image for the user 'user'.
     * @param user
     * @param file File with new image.
     * @return Url to new image.
     * @throws ServiceException
     */
    String changeImage(User user, File file) throws ServiceException;

    /**
     * Edit user params
     * @param user
     * @param name New name for the user.
     * @param surname New surname for the user
     * @param email New email for the user.
     * @return User.
     * @throws ServiceException
     */
    User editUser(User user, String name, String surname, String email) throws ServiceException;
}
