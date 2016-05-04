package by.epam.likeit.dao;

import by.epam.likeit.dao.impl.AnswerDAOImpl;

/**
 * Created by Пользователь on 04.05.2016.
 */
public class AnswerDAOFactory {
    private static AnswerDAO answerDAO = new AnswerDAOImpl();

    public static AnswerDAO getInstance(){
        return answerDAO;
    }
}
