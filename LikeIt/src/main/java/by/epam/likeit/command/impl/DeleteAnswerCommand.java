package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.AnswerService;
import by.epam.likeit.service.ServiceFactory;
import by.epam.likeit.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAnswerCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String METHOD = "method";
    private static final String AJAX = "ajax";
    private static final String ID = "id";
    private static final String USER = "user";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User) request.getSession().getAttribute(USER);
        int id = Integer.parseInt(request.getParameter(ID));

        ServiceFactory factory = ServiceFactory.getInstance();
        AnswerService answerService = factory.getAnswerService();

        try {
            answerService.deleteAnswer(id, user);
            request.setAttribute(METHOD, AJAX);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return null;
    }
}
