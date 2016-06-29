package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.ServiceFactory;
import by.epam.likeit.service.TopicService;
import by.epam.likeit.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTopicCommand implements Command {

    private static final String USER = "user";
    private static final String VALUE = "value";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = PageName.ERROR_PAGE;

        User user = (User) request.getSession().getAttribute(USER);
        String value = request.getParameter(VALUE);

        ServiceFactory factory = ServiceFactory.getInstance();
        TopicService topicService = factory.getTopicService();

        try {
            topicService.addTopic(user, value);
            page = PageName.TOPIC_MANAGEMENT_PAGE;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
