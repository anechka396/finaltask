package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.dao.BaseDAO;
import by.epam.likeit.dao.QuestionDAO;
import by.epam.likeit.dao.UserDAO;
import by.epam.likeit.dao.exception.DaoException;
import by.epam.likeit.dao.impl.QuestionDAOImpl;
import by.epam.likeit.dao.impl.UserDAOImpl;
import by.epam.likeit.entity.Question;
import by.epam.likeit.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Пользователь on 25.04.2016.
 */
public class ShowLastQuestionsCommand implements Command {
    private static final Logger log = LogManager.getRootLogger();
    private static final QuestionDAO dao = new QuestionDAOImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<Question> questions = null;
        try {
            questions = dao.retrieveAll();
          //  log.trace("Отработало DAO");
            request.setAttribute("questions", questions);

        } catch (DaoException e) {
            throw new CommandException(e);
        }
        return PageName.INDEX_PAGE;
    }
}
