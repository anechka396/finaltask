package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.command.PageName;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.UserService;
import by.epam.likeit.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Пользователь on 17.04.2016.
 */
public class LoginCommand implements Command {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;

        try {
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            User user = UserService.checkLogin(login, password);
            if (user != null){
                request.getSession(true).setAttribute(LOGIN, user);
                page = PageName.USER_PAGE;
            } else{
                page = PageName.ERROR_PAGE;
            }
        } catch (ServiceException e) {
            throw new CommandException("", e);
        }

        return page;
    }
}
