package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.dao.QuestionDAO;
import by.epam.likeit.dao.QuestionDAOFactory;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.QuestionService;
import by.epam.likeit.service.ServiceFactory;
import by.epam.likeit.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuestionCommand implements Command{
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String METHOD = "method";
    private static final String AJAX = "ajax";
    private static final String ID = "id";
    private static final String USER = "user";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User) request.getSession().getAttribute(USER);
        int id = Integer.parseInt(request.getParameter(ID));

        ServiceFactory factory = ServiceFactory.getInstance();
        QuestionService questionService = factory.getQuestionService();

        try {
            questionService.deleteQuestion(user, id);
            request.setAttribute(METHOD, AJAX);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return null;
    }
}
