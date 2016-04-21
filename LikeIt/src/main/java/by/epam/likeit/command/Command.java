package by.epam.likeit.command;

import by.epam.likeit.command.exception.CommandException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Пользователь on 17.04.2016.
 */
public interface Command {
    public String execute(HttpServletRequest request) throws CommandException;
}
