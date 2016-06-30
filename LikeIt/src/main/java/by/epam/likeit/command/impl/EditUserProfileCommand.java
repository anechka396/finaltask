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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditUserProfileCommand implements Command{
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String USER = "user";
    private static final String LOGIN = "login";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String METHOD = "method";
    private static final String REDIRECT = "redirect";
    private static final String EMPTY = "empty";
    private static final String ERROR = "error";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        request.removeAttribute(ERROR);
        String page = PageName.ERROR_PAGE;

        User user = (User) request.getSession().getAttribute(USER);
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String email = request.getParameter(EMAIL);

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

        User newUser = null;
        try {
            newUser = userService.editUser(user, name, surname, email);
            request.getSession(true).setAttribute(USER, newUser);
            page = PageName.USER_PAGE;
            request.setAttribute(METHOD, REDIRECT);
        } catch (ServiceException e) {
            String message = e.getMessage();
            if(!message.equals(EMPTY)){
                page = PageName.EDIT_USER_PAGE;
                request.setAttribute(ERROR, message);
            } else {
                throw new CommandException(e);
            }
        }

        return page;
    }
}
