package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.dao.QuestionDAO;
import by.epam.likeit.dao.QuestionDAOFactory;
import by.epam.likeit.dao.TopicDAO;
import by.epam.likeit.dao.TopicDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.ServiceFactory;
import by.epam.likeit.service.TopicService;
import by.epam.likeit.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTopicCommand implements Command{

    private static final String ID = "pk";
    private static final String USER = "user";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = PageName.ERROR_PAGE;

        User user = (User) request.getSession().getAttribute(USER);
        int id = Integer.parseInt(request.getParameter(ID));

        ServiceFactory factory = ServiceFactory.getInstance();
        TopicService topicService = factory.getTopicService();

        try {
            topicService.deleteTopic(user, id);
            page = PageName.TOPIC_MANAGEMENT_PAGE;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
