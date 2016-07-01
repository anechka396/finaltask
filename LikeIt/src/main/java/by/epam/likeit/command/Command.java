package by.epam.likeit.command;

import by.epam.likeit.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Command is interface for the command.
 * @author Anna Yakubenko
 * @author anechka396@mail.ru
 * @version 1.0
 */
public interface Command {
    /**
     * This method execute command. Process request and response and returns the name of page for result.
     * @param request Http servlet request
     * @param response Http servlet responce
     * @return The name of page for result.
     * @throws CommandException
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
