package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.dao.AnswerDAO;
import by.epam.likeit.dao.AnswerDAOFactory;
import by.epam.likeit.dao.QuestionDAO;
import by.epam.likeit.dao.QuestionDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Пользователь on 06.05.2016.
 */
public class DeleteAnswerCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String METHOD = "method";
    private static final String AJAX = "ajax";
    private static final String ID = "id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        AnswerDAO answerDAO = AnswerDAOFactory.getInstance();
        int id = Integer.parseInt(request.getParameter(ID));
        try {
            answerDAO.delete(id);
            request.setAttribute(METHOD, AJAX);
        } catch (DaoException e) {
            throw new CommandException(e);
        }
        LOGGER.trace("In delete " + id);
        return null;
    }
}
