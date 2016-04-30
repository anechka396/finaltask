package by.epam.likeit.dao;

import by.epam.likeit.dao.impl.QuestionDAOImpl;

/**
 * Created by Пользователь on 30.04.2016.
 */
public class QuestionDAOFactory {
    private static QuestionDAO questionDAO = new QuestionDAOImpl();

    public static QuestionDAO getInstance(){
        return questionDAO;
    }
}
