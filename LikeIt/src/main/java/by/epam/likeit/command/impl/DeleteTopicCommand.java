package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.dao.QuestionDAO;
import by.epam.likeit.dao.QuestionDAOFactory;
import by.epam.likeit.dao.TopicDAO;
import by.epam.likeit.dao.TopicDAOFactory;
import by.epam.likeit.dao.exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTopicCommand implements Command{

    private static final String ID = "pk";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = PageName.ERROR_PAGE;
        TopicDAO topicDAO = TopicDAOFactory.getInstance();
        int id = Integer.parseInt(request.getParameter(ID));
        try {
            topicDAO.delete(id);
            page = PageName.TOPIC_MANAGEMENT_PAGE;
        } catch (DaoException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
