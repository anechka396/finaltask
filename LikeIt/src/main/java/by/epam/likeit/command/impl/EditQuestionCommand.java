package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.service.exception.ServiceException;
import by.epam.likeit.service.impl.EditQuestionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Created by Пользователь on 13.05.2016.
 */
public class EditQuestionCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String METHOD = "method";
    private static final String AJAX = "ajax";
    private static final String ID = "pk";
    private static final String VALUE = "value";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        request.setAttribute(METHOD, AJAX);
        int id = Integer.parseInt(request.getParameter(ID));
        String value = request.getParameter(VALUE);
        EditQuestionService service = new EditQuestionService();
        try {
            service.service(id, value);
        } catch (ServiceException e) {
            throw  new CommandException(e);
        }
        return null;
    }
}
