package by.epam.likeit.filter;

import by.epam.likeit.command.PageName;
import by.epam.likeit.entity.User;
import by.epam.likeit.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter which limit access for unauthorized users.
 * @author Anna Yakubenko
 * @author anechka396@mail.ru
 * @version 1.0
 */
public class UserSecurityFilter implements Filter {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String USER = "user";
    private static final String ERROR_ACCESS_DENIED = "prop.access.denied";

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
            Util.localizeMessage(ERROR_ACCESS_DENIED, req);
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(PageName.ERROR_PAGE);
            dispatcher.forward(req, resp);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
