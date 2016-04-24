package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.RegistrationService;
import by.epam.likeit.service.UserService;
import by.epam.likeit.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Пользователь on 19.04.2016.
 */
public class RegistrationCommand implements Command {

    private static RegistrationService service = new RegistrationService();
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String EMAIL = "email";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = PageName.ERROR_PAGE;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(NAME);
        String email = request.getParameter(EMAIL);
            User user = service.service(login, password, name, email, "user");
            request.getSession(true).setAttribute(LOGIN, user);
            page = PageName.USER_PAGE;
        return page;
    }
}
