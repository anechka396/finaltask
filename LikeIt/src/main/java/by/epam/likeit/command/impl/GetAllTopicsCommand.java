package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.dao.TopicDAO;
import by.epam.likeit.dao.TopicDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.dao.impl.TopicDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Пользователь on 29.04.2016.
 */
public class GetAllTopicsCommand implements Command {
    private static final Logger logger = LogManager.getRootLogger();

    private static final String METHOD = "method";
    private static final String AJAX = "ajax";
    private static final String TOPICS = "topics";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<String> topics = new ArrayList<>();
        JSONArray ar = new JSONArray();
        JSONObject resultJson = new JSONObject();

        try {
            TopicDAO topicDAO = TopicDAOFactory.getInstance();
            topics = topicDAO.retrieveAll();
            request.setAttribute(METHOD, AJAX);

            for (String s : topics){
                ar.put(s);
            }

            resultJson.put(TOPICS, ar);
            request.setAttribute(TOPICS, resultJson);

        } catch (DaoException e) {
            throw new CommandException(e);
        }

        return null;
    }
}
