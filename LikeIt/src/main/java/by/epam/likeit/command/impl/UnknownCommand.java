package by.epam.likeit.command.impl;

import by.epam.likeit.command.Command;
import by.epam.likeit.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Пользователь on 17.04.2016.
 */
public class UnknownCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
