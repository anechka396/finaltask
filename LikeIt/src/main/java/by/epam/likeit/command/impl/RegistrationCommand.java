package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.impl.RegistrationService;
import by.epam.likeit.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {

    private static final RegistrationService service = new RegistrationService();
    private static final String USER = "user";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String METHOD = "method";
    private static final String REDIRECT = "redirect";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = PageName.ERROR_PAGE;

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(NAME);
        String email = request.getParameter(EMAIL);

        User user = null;
        try {
            user = service.service(login, password, name, email, USER);
            request.getSession(true).setAttribute(USER, user);
            page = PageName.USER_PAGE;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        request.setAttribute(METHOD, REDIRECT);

        return page;
    }
}
