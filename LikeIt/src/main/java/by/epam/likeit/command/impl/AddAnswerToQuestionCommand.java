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
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddAnswerToQuestionCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String ID = "id";
    private static final String TEXT = "text";
    private static final String METHOD = "method";
    private static final String AJAX = "ajax";
    private static final String USER = "user";
    private static final String ANSWERS = "answers";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int id = Integer.parseInt(request.getParameter(ID));
        String text = request.getParameter(TEXT);
        User user = (User) request.getSession().getAttribute(USER);
        request.setAttribute(METHOD, AJAX);

        AddAnswerService service = new AddAnswerService();
        try {
            List<Answer> answers = service.service(id, text, user.getLogin());
            Gson gson = new Gson();
            String json = gson.toJson(answers);
            LOGGER.trace(json);
            response.setContentType("application/json");
            response.getWriter().write(json);
        } catch (ServiceException e) {
            throw new CommandException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PageName.QUESION_PAGE;
    }
}
