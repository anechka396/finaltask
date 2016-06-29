package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.dao.UserDAO;
import by.epam.likeit.dao.UserDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetUserRatingCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String LOGIN = "login";
    private static final String METHOD = "method";
    private static final String AJAX = "ajax";
    private static final String CONTENT_TYPE = "text/html";
    private static final String EMPTY = "";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        double rating = 0;
        String login = request.getParameter(LOGIN);
        UserDAO userDAO = UserDAOFactory.getInstance();
        try {
            int sum = userDAO.getSumOfMarksByLogin(login);
            int count = userDAO.getCountOfMarksByLogin(login);
            if(count != 0)
                rating = (double)sum / count;
            response.setContentType(CONTENT_TYPE);
            response.getWriter().write(EMPTY + rating);
        } catch (DaoException e) {
            throw new CommandException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        request.setAttribute(METHOD, AJAX);
        return null;
    }
}
