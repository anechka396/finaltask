package by.epam.likeit.controller;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.controller.helper.CommandHelper;
import by.epam.likeit.controller.helper.InitCommandHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final CommandHelper helper = new CommandHelper();
    private static final String COMMAND_NAME =  "command";
    private static final Logger LOGGER = LogManager.getRootLogger();

    public Controller() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        InitCommandHelper initCommandHelper = new InitCommandHelper();
        initCommandHelper.init(helper);
        LOGGER.trace("init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = null;
        Command command = null;
        String page = null;

        try {
            commandName = req.getParameter(COMMAND_NAME);
            command = helper.getCommand(commandName);
            page = command.execute(req);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        if (dispatcher != null){
            dispatcher.forward(req, resp);
        }else{
            // to do
        }

    }
}
