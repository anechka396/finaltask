package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.command.PageName;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    private static final String USER = "user";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = PageName.INDEX_PAGE;
        request.getSession().removeAttribute(USER);
        request.getSession().invalidate();
        return page;
    }
}
