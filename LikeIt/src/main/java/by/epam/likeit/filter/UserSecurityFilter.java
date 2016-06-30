package by.epam.likeit.filter;

import by.epam.likeit.command.PageName;
import by.epam.likeit.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Пользователь on 30.06.2016.
 */
public class UserSecurityFilter implements Filter {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String USER = "user";
    private static final String ERROR = "error";
    private static final String ERROR_ACCESS_DENIED = "prop.access.denied";
    private static final String RU = "ru";
    private static final String LOCALE = "locale";
    private static final String PATH_TO_PROP_FILE = "localization/prop";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER);
        if(user == null){
            ResourceBundle bundle =  ResourceBundle.getBundle(PATH_TO_PROP_FILE, getLocale(req));
            req.setAttribute(ERROR, bundle.getString(ERROR_ACCESS_DENIED));
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(PageName.ERROR_PAGE);
            dispatcher.forward(req, resp);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private Locale getLocale(HttpServletRequest request){
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
