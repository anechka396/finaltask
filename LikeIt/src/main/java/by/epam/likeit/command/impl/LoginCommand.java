package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.command.PageName;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.LoginService;
import by.epam.likeit.service.UserService;
import by.epam.likeit.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static final LoginService service = new LoginService();
    private static final String USER = "user";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        User user = null;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        try {
            user = service.service(login, password);
        } catch (ServiceException e) {
            throw new CommandException();
        }

        if (user != null) {
            request.getSession(true).setAttribute(USER, user);
            page = PageName.USER_PAGE;
        } else {
            page = PageName.ERROR_PAGE;
        }

        return page;
    }
}
