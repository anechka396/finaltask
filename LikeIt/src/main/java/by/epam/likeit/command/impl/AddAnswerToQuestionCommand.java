package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.dao.AnswerDAO;
import by.epam.likeit.dao.AnswerDAOFactory;
import by.epam.likeit.entity.Answer;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.exception.ServiceException;
import by.epam.likeit.service.impl.AddAnswerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Пользователь on 04.05.2016.
 */
public class AddAnswerToQuestionCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String ID = "id";
    private static final String TEXT = "text";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        request.setAttribute("method", "ajax");
        int id = Integer.parseInt(request.getParameter(ID));
        String text = request.getParameter(TEXT);
        User user = (User) request.getSession().getAttribute("user");
        AddAnswerService service = new AddAnswerService();
        try {
            service.service(id, text, user.getLogin());
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PageName.QUESION_PAGE;
    }
}
