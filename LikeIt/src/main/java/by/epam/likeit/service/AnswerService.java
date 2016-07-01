package by.epam.likeit.service;

import by.epam.likeit.entity.User;
import by.epam.likeit.service.exception.ServiceException;

public interface AnswerService {
    /**
     * Add new answer.
     * @param id Id of the question.
     * @param text Text of the answer.
     * @param user Author of the answer
     * @throws ServiceException
     */
    void addAnswer(int id, String text, User user) throws ServiceException;

    /**
     * Delete answer.
     * @param id Id of the question.
     * @param user User who delete answer.
     * @throws ServiceException
     */
    void deleteAnswer(int id, User user) throws ServiceException;

    /**
     * Set mark to answer by user.
     * @param id Id of the answer.
     * @param user User who set mark.
     * @param mark Mark value.
     * @throws ServiceException
     */
    void setRating(int id, User user, int mark) throws ServiceException;
}
