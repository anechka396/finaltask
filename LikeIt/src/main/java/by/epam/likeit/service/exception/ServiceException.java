package by.epam.likeit.service.exception;

/**
 * Created by Пользователь on 17.04.2016.
 */
public class ServiceException extends Exception {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
