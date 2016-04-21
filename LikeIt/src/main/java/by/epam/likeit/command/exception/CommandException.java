package by.epam.likeit.command.exception;

/**
 * Created by Пользователь on 17.04.2016.
 */
public class CommandException extends Exception {
    public CommandException() {
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
