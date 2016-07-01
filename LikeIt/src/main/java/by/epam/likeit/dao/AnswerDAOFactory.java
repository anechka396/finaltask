package by.epam.likeit.dao;

import by.epam.likeit.dao.impl.AnswerDAOImpl;

/**
 * Returns the realization of class AnswerDAO.
 * @author Anna Yakubenko
 * @author anechka396@mail.ru
 * @version 1.0
 */
public class AnswerDAOFactory {
    private static final AnswerDAO answerDAO = new AnswerDAOImpl();

    public static AnswerDAO getInstance(){
        return answerDAO;
    }
}
