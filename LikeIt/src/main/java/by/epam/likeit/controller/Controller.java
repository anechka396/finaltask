package by.epam.likeit.controller;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.PageName;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.controller.helper.CommandHelper;
import by.epam.likeit.controller.helper.InitCommandHelper;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {

    private static final CommandHelper helper = new CommandHelper();
    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String COMMAND_NAME =  "command";
    private static final String COMMAND_XML = "commandXML";
    private static final String METHOD = "method";
    private static final String AJAX = "ajax";
    private static final String REDIRECT = "redirect";
    private static final String CHANGE_IMAGE = "change-image";

    public Controller() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        String fileName = config.getInitParameter(COMMAND_XML);
        InitCommandHelper initCommandHelper = new InitCommandHelper();
        initCommandHelper.init(helper, fileName);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = processRequest(req, resp);
        sendRequest(req, resp, page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = PageName.USER_PAGE;
        String pathInfo = req.getPathInfo();
        if(pathInfo != null){
            page = executeCommand(CHANGE_IMAGE, req, resp);
        } else {
            page = processRequest(req, resp);
        }
        sendRequest(req, resp, page);
    }

    private String processRequest(HttpServletRequest req, HttpServletResponse resp){
        String commandName = null;
        String page = null;

        commandName = req.getParameter(COMMAND_NAME);
        LOGGER.trace(commandName);
        page = executeCommand(commandName, req, resp);

        return page;
    }

    private String executeCommand(String commandName, HttpServletRequest req, HttpServletResponse resp){
        Command command = helper.getCommand(commandName);
        String page = null;
        try {
            page = command.execute(req, resp);
        } catch (CommandException e) {
            LOGGER.error(e);
        }
        return page;
    }

    private void sendRequest(HttpServletRequest req, HttpServletResponse resp, String page) throws IOException, ServletException {
        String method = (String) req.getAttribute(METHOD);

        if( method == null ){
            RequestDispatcher dispatcher = req.getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        } else if ( method.equals(REDIRECT) ){
            req.removeAttribute(METHOD);
            resp.sendRedirect(page);
        } else if( method.equals(AJAX) ){
            req.removeAttribute(METHOD);
        }
    }
}
