package by.epam.likeit.dao.pool;

/** The names of parameters in property file for database.
 * @author Anna Yakubenko
 * @author anechka396@mail.ru
 * @version 1.0
 */
public final class DBParameter {
    private DBParameter() {};

    public static final String DB_DRIVER = "db.driver";
    public static final String DB_URL = "db.url";
    public static final String DB_USER = "db.user";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_POOL_SIZE = "db.poolsize";
}
