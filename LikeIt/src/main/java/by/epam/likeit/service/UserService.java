package by.epam.likeit.service;

import by.epam.likeit.entity.User;
import by.epam.likeit.service.exception.ServiceException;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

import java.io.File;

public interface  UserService {
    User loginUser(String login, String password) throws ServiceException;
    User registerUser(String login, String password, String repeatPassword, String name, String surname, String email, String role) throws ServiceException;
    void changePassword(User user, String oldPassword, String newPassword, String repeatNewPassword) throws ServiceException;
    String changeImage(User user, File file) throws ServiceException;
    User editUser(User user, String name, String surname, String email) throws ServiceException;
}
