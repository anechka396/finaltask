package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.command.PageName;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.ServiceFactory;
import by.epam.likeit.service.UserService;
import by.epam.likeit.service.exception.ServiceException;
import by.epam.likeit.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.jackson.ListOfMapEntryDeserializer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String USER = "user";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ERROR = "error";
    private static final String METHOD = "method";
    private static final String REDIRECT = "redirect";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        request.removeAttribute(ERROR);
        String page = PageName.USER_PAGE;
        User user = null;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        try {
            user = userService.loginUser(login, password);
            request.getSession(true).setAttribute(USER, user);
            request.setAttribute(METHOD, REDIRECT);
        } catch (ServiceException e) {
            page = Util.processErrorMessage(e, request, PageName.LOGIN_PAGE);
        }
        return page;
    }
}
