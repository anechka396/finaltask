package by.epam.likeit.controller;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.controller.helper.CommandHelper;
import by.epam.likeit.controller.helper.InitCommandHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final CommandHelper helper = new CommandHelper();
    private static final String COMMAND_NAME =  "command";
    private static final String METHOD = "method";
    private static final Logger LOGGER = LogManager.getRootLogger();

    public Controller() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        String fileName = config.getInitParameter("commandXML");
        InitCommandHelper initCommandHelper = new InitCommandHelper();
        initCommandHelper.init(helper, fileName);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = processRequest(req);
      //  resp.sendRedirect(page);
        RequestDispatcher dispatcher = req.getRequestDispatcher(PageName.INDEX_PAGE);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = processRequest(req);

        if(page == null) {
            resp.sendRedirect(PageName.ERROR_PAGE);
            return;
        }

        String method = (String) req.getAttribute(METHOD);
        if(method != null && method.equals("redirect")){
            req.removeAttribute(METHOD);
            resp.sendRedirect(page);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        }
    }


    private String processRequest(HttpServletRequest req){
        String commandName = null;
        Command command = null;
        String page = null;

        try {
            commandName = req.getParameter(COMMAND_NAME);
            LOGGER.trace(commandName);
            command = helper.getCommand(commandName);
            page = command.execute(req);
        } catch (CommandException e) {
            LOGGER.error(e);
        }

        return page;
    }
}
