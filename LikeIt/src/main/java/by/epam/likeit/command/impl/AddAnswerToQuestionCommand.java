package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.Service;
import by.epam.likeit.service.ServiceFactory;
import by.epam.likeit.service.ServiceName;
import by.epam.likeit.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAnswerToQuestionCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String ID = "id";
    private static final String TEXT = "text";
    private static final String USER = "user";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int id = Integer.parseInt(request.getParameter(ID));
        String text = request.getParameter(TEXT);
        User user = (User) request.getSession().getAttribute(USER);

        ServiceFactory factory = ServiceFactory.getInstance();
        Service addAnswerService = factory.getService(ServiceName.ADD_ANSWER);
        try {
           addAnswerService.service(id, text, user.getLogin());
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PageName.QUESION_PAGE;
    }
}
