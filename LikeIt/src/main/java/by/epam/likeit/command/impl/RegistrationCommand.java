package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.ServiceFactory;
import by.epam.likeit.service.UserService;
import by.epam.likeit.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String USER = "user";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String PASSWORD_2 = "password2";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String METHOD = "method";
    private static final String REDIRECT = "redirect";
    private static final String EMPTY = "empty";
    private static final String ERROR = "error";
    private static final String RU = "ru";
    private static final String LOCALE = "locale";
    private static final String PATH_TO_PROP_FILE = "localization/prop";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        request.removeAttribute(ERROR);
        String page = PageName.ERROR_PAGE;

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String repeatPassword = request.getParameter(PASSWORD_2);
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String email = request.getParameter(EMAIL);

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

        User user = null;
        try {
            user = userService.registerUser(login, password, repeatPassword, name,surname, email, USER);
            request.getSession(true).setAttribute(USER, user);
            page = PageName.USER_PAGE;
            request.setAttribute(METHOD, REDIRECT);
        } catch (ServiceException e) {
            String message = e.getMessage();
            if(!message.equals(EMPTY)){
                ResourceBundle bundle =  ResourceBundle.getBundle(PATH_TO_PROP_FILE, getLocale(request));
                request.setAttribute(ERROR, bundle.getString(message));
                page = PageName.LOGIN_PAGE;
            } else {
                throw new CommandException(e);
            }
        }

        return page;
    }

    private Locale getLocale(HttpServletRequest request){
        Locale locale = new Locale(RU);
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(LOCALE)) {
                    locale = new Locale(cookie.getValue());
                }
            }
        }
        return locale;
    }
}
