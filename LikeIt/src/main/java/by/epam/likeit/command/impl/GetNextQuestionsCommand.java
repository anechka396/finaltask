package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.dao.QuestionDAO;
import by.epam.likeit.dao.QuestionDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Question;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class GetNextQuestionsCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String LAST_DATE = "lastDate";
    private static final String TOPIC = "topic";
    private static final String METHOD = "method";
    private static final String AJAX = "ajax";
    private static final String CONTENT_TYPE = "application/json";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        List<Question> questions = null;
        String topic = null;
        Timestamp lastDate = Timestamp.valueOf(request.getParameter(LAST_DATE));
      //  LOGGER.trace(lastDate);

        try {
            QuestionDAO questionDAO = QuestionDAOFactory.getInstance();
            topic = request.getParameter(TOPIC);
            if(topic != null){
                questions = questionDAO.retrieveNext(topic, lastDate);
            } else {
                questions = questionDAO.retrieveNext(lastDate);
            }

            for(Question q: questions){
                LOGGER.trace(q.getText());
            }

            Gson gson = new Gson();
            String json = gson.toJson(questions);

            LOGGER.trace(json);

            request.setAttribute(METHOD, AJAX);
            response.setContentType(CONTENT_TYPE);
            response.getWriter().write(json);

        } catch (DaoException e) {
            throw new CommandException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
