package by.epam.likeit.command;

import by.epam.likeit.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
