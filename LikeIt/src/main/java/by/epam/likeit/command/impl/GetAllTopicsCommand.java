package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetAllTopicsCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String METHOD = "method";
    private static final String AJAX = "ajax";
    private static final String TOPICS = "topics";
    private static final String CONTENT_TYPE = "application/json";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        List<Topic> topics = new ArrayList<>();
        JSONArray ar = new JSONArray();
        JSONObject resultJson = new JSONObject();

        try {
            TopicDAO topicDAO = TopicDAOFactory.getInstance();
            topics = topicDAO.retrieveAll();
            request.setAttribute(METHOD, AJAX);

            for (Topic topic : topics){
                ar.put(topic.getValue());
            }

            resultJson.put(TOPICS, ar);
            response.setContentType(CONTENT_TYPE);
            response.getWriter().write(resultJson.toString());
        } catch (DaoException | IOException e) {
            throw new CommandException(e);
        }

        return null;
    }
}
