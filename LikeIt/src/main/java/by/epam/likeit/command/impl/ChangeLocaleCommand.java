package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.command.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Пользователь on 17.04.2016.
 */
public class ChangeLocaleCommand implements Command {
    private static final String LOCALE = "locale";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.getSession(true).setAttribute(LOCALE, request.getParameter(LOCALE));
        return PageName.INDEX_PAGE;
    }
}
