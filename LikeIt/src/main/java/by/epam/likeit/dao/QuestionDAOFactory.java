package by.epam.likeit.dao;

import by.epam.likeit.dao.impl.QuestionDAOImpl;

/**
 * Returns the realization of class QuestionDAO.
 * @author Anna Yakubenko
 * @author anechka396@mail.ru
 * @version 1.0
 */
public class QuestionDAOFactory {
    private static QuestionDAO questionDAO = new QuestionDAOImpl();

    public static QuestionDAO getInstance(){
        return questionDAO;
    }
}
