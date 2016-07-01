package by.epam.likeit.util;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class Util {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String RU = "ru";
    private static final String LOCALE = "locale";
    private static final String PATH_TO_PROP_FILE = "localization/prop";
    private static final String SPACE = " ";
    private static final String ALERT = "alert";
    private static final String ERROR = "error";

    public static String processErrorMessage(ServiceException e, HttpServletRequest request, String page1) throws CommandException {
        String page = PageName.ERROR_PAGE;
        String message = e.getMessage();

        String mas[] = message.split(SPACE);
        if(mas[0].equals(ALERT)){
            localizeMessage(mas[1], request);
            page = page1;
        } else {
            throw new CommandException(e);
        }
        return page;
    }

    public static void localizeMessage(String message, HttpServletRequest request){
        ResourceBundle bundle =  ResourceBundle.getBundle(PATH_TO_PROP_FILE, getLocaleFromCookie(request));
        request.setAttribute(ERROR, bundle.getString(message));
    }

    public static Locale getLocaleFromCookie(HttpServletRequest request){
        Locale locale = new Locale(RU);
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(LOCALE)) {
                    locale = new Locale(cookie.getValue());
                }
            }
        }
        return locale;
    }
}
