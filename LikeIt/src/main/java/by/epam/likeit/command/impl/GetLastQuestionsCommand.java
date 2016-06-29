package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.dao.QuestionDAO;
import by.epam.likeit.dao.QuestionDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetLastQuestionsCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String QUESTIONS = "questions";
    private static final String TOPIC = "topic";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        List<Question> questions = null;
        String topic = null;
        try {
            QuestionDAO questionDAO = QuestionDAOFactory.getInstance();
            topic = request.getParameter(TOPIC);
            if(topic != null){
                questions = questionDAO.retrieveLast(topic);
            } else {
                questions = questionDAO.retrieveLast();
            }
            request.setAttribute(QUESTIONS, questions);

        } catch (DaoException e) {
            throw new CommandException(e);
        }
        return PageName.INDEX_PAGE;
    }
}
