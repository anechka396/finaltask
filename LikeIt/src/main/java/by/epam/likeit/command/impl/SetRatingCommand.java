package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.exception.ServiceException;
import by.epam.likeit.service.impl.SetRatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;

/**
 * Created by Пользователь on 08.05.2016.
 */
public class SetRatingCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String METHOD = "method";
    private static final String AJAX = "ajax";
    private static final String ID = "id";
    private static final String USER = "user";
    private static final String MARK = "mark";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int id = Integer.parseInt(request.getParameter(ID));
        User author = (User) request.getSession().getAttribute(USER);
        int mark = Integer.parseInt(request.getParameter(MARK));
        LOGGER.trace(id+ " "  + author + " " + mark);
        SetRatingService serv = new SetRatingService();
        try {
            serv.service(id, author, mark);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(METHOD, AJAX);
        return null;
    }
}
