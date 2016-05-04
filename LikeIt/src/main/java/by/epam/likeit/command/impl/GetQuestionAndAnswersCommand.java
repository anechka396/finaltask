package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.dao.AnswerDAO;
import by.epam.likeit.dao.AnswerDAOFactory;
import by.epam.likeit.dao.QuestionDAO;
import by.epam.likeit.dao.QuestionDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.Answer;
import by.epam.likeit.entity.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetQuestionAndAnswersCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String ID = "id";
    private static final String QUESTION = "question";
    private static final String ANSWERS = "answers";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int id = Integer.parseInt(request.getParameter(ID));
        LOGGER.trace(id);
        QuestionDAO questionDAO = QuestionDAOFactory.getInstance();
        AnswerDAO answerDAO = AnswerDAOFactory.getInstance();
        try {
            Question question =  questionDAO.retrieve(id);
            List<Answer> answers = answerDAO.retrieveAllByQuestionId(id);
            request.setAttribute(QUESTION, question);
            request.setAttribute(ANSWERS, answers);
        } catch (DaoException e) {
            throw new CommandException(e);
        }
        return PageName.QUESION_PAGE;
    }
}
