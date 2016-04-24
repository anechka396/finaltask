package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.command.PageName;

import javax.servlet.http.HttpServletRequest;

public class ChangeLocaleCommand implements Command {
    private static final String LOCALE = "locale";
    private static final String METHOD = "method";
    private static final String REDIRECT = "redirect";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.getSession(true).setAttribute(LOCALE, request.getParameter(LOCALE));
        request.setAttribute(METHOD, REDIRECT);
        return PageName.INDEX_PAGE;
    }
}
