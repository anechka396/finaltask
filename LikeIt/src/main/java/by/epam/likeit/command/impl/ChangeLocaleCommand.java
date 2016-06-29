package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLocaleCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String LOCALE = "locale";
    private static final String METHOD = "method";
    private static final String REDIRECT = "redirect";
    private static final String REFERER = "referer";
    private static final String CONTROLLER = "Controller";
    private static final String SLASH = "/";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String referer = request.getHeader(REFERER);
        String page = getPageName(referer);
        request.getSession(true).setAttribute(LOCALE, request.getParameter(LOCALE));
        request.setAttribute(METHOD, REDIRECT);
        return page.equals(CONTROLLER) ? PageName.INDEX_PAGE : page;
    }

    private String getPageName(String referer){
        String [] mas = referer.split(SLASH);
        return mas[mas.length - 1];
    }
}
