package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.command.PageName;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.Service;
import by.epam.likeit.service.ServiceFactory;
import by.epam.likeit.service.ServiceName;
import by.epam.likeit.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    private static final String USER = "user";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page;
        User user = null;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        ServiceFactory factory = ServiceFactory.getInstance();
        Service loginService = factory.getService(ServiceName.LOGIN);
        try {
            user = loginService.service(login, password);
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
