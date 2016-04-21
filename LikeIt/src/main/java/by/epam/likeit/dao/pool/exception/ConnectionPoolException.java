package by.epam.likeit.dao.pool.exception;

/**
 * Created by Пользователь on 19.04.2016.
 */
public class ConnectionPoolException extends Exception {
    public ConnectionPoolException() {
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }

    public ConnectionPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
