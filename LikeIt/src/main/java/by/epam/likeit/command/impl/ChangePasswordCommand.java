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

public class ChangePasswordCommand implements Command {

    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String USER = "user";
    private static final String METHOD = "method";
    private static final String REDIRECT = "redirect";
    private static final String OLD_PASSWORD = "old-password";
    private static final String NEW_PASSWORD = "new-password";
    private static final String REPEAT_NEW_PASSWORD = "new-password-2";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = PageName.ERROR_PAGE;

        User user = (User) request.getSession().getAttribute(USER);
        String oldPassword = request.getParameter(OLD_PASSWORD);
        String newPassword = request.getParameter(NEW_PASSWORD);
        String repeatNewPassword = request.getParameter(REPEAT_NEW_PASSWORD);

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        try {
            userService.changePassword(user.getLogin(), oldPassword, newPassword, repeatNewPassword);
            request.setAttribute(METHOD, REDIRECT);
            page = PageName.USER_PAGE;
        } catch (ServiceException e) {
            new CommandException(e);
        }

        return page;
    }
}
