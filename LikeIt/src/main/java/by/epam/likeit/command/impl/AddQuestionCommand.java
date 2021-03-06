package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.QuestionService;
import by.epam.likeit.service.ServiceFactory;
import by.epam.likeit.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddQuestionCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String USER = "user";
    private static final String TEXT = "text";
    private static final String TOPIC = "topic";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = PageName.INDEX_PAGE;

        User user = (User) request.getSession().getAttribute(USER);
        String text = request.getParameter(TEXT);
        String topic = request.getParameter(TOPIC);

        ServiceFactory factory = ServiceFactory.getInstance();
        QuestionService questionService = factory.getQuestionService();

        try {
            questionService.addQuestion(user, topic, text);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
