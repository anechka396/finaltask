package by.epam.likeit.listener;

import by.epam.likeit.dao.pool.ConnectionPool;
import by.epam.likeit.dao.pool.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Initialize connection pool when servlet start().
 * @author Anna Yakubenko
 * @author anechka396@mail.ru
 * @version 1.0
 */
public class InitializeListener implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getRootLogger();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ConnectionPool.getInstance().initPoolDate();
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            ConnectionPool.getInstance().dispose();
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        }
    }
}
