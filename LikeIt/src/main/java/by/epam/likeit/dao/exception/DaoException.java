package by.epam.likeit.dao.exception;

/**
 * Created by Пользователь on 17.04.2016.
 */
public class DaoException extends Exception {
    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
