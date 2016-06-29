package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.dao.TopicDAO;
import by.epam.likeit.dao.TopicDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Topic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GetTopicsCommand implements Command{
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String TOPICS = "topics";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = PageName.ERROR_PAGE;
        List<Topic> topics = new ArrayList<>();

        try {
            TopicDAO topicDAO = TopicDAOFactory.getInstance();
            topics = topicDAO.retrieveAll();
            request.setAttribute(TOPICS, topics);
            page = PageName.TOPIC_MANAGEMENT_PAGE;
        } catch (DaoException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
