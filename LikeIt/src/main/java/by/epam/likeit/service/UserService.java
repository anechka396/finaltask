package by.epam.likeit.service;

import by.epam.likeit.entity.User;
import by.epam.likeit.service.exception.ServiceException;

public interface  UserService {
    User loginUser(String login, String password) throws ServiceException;
    User registerUser(String login, String password, String name, String surname, String email, String role) throws ServiceException;
    void changePassword(String login, String oldPassword, String newPassword, String repeatNewPassword) throws ServiceException;
}
