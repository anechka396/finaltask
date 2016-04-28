package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.entity.User;
import by.epam.likeit.service.AddQuestionService;
import by.epam.likeit.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class AddQuestionCommand implements Command {
    private static final AddQuestionService service = new AddQuestionService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        PageName pageName = null;
        User user = (User) request.getSession().getAttribute("user");
        String text = request.getParameter("text");
        String topic = "Cars";
        try {
            service.service(user, topic, text);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PageName.INDEX_PAGE;
    }
}
