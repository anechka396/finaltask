package by.epam.likeit.controller;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;
import by.epam.likeit.controller.helper.CommandHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final String COMMAND_NAME =  "command";
    private static final Logger LOGGER = LogManager.getRootLogger();

    public Controller() {
        super();
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
       //     LOGGER.trace(commandName);
            CommandHelper helper = new CommandHelper();
            command = helper.getCommand(commandName);
            page = command.execute(req);
        } catch (CommandException e) {
            e.printStackTrace();
        }

     //   LOGGER.trace(page);

        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        if (dispatcher != null){
            dispatcher.forward(req, resp);//obj.meth()
        }else{
            // to do
        }

    }
}
