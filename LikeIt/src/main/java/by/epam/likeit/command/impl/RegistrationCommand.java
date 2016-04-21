package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.UserService;
import by.epam.likeit.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Пользователь on 19.04.2016.
 */
public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = PageName.ERROR_PAGE;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        try {
            User user = UserService.addUser(login, password, repeatPassword, name, email, "user");
            request.getSession(true).setAttribute("login", user);
            page = PageName.USER_PAGE;
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
