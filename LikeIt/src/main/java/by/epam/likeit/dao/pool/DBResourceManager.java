package by.epam.likeit.dao.pool;

import java.util.ResourceBundle;
/** This class manage with params in property file for database. The name of property file "database".
 * @author Anna Yakubenko
 * @author anechka396@mail.ru
 * @version 1.0
 */
public class DBResourceManager {
    private static final String DB = "database";
    private static final DBResourceManager instance = new DBResourceManager();
    private ResourceBundle bundle = ResourceBundle.getBundle(DB);

    /**
     * Returns the instance of DBResourceManager (Singleton).
     * @return
     */
    public static DBResourceManager getInstance(){
        return instance;
    }

    /**
     * Return the value from property file by key.
     * @param key Key of param.
     * @return Value of param.
     */
    public String getValue(String key){
        return bundle.getString(key);
    }
}
