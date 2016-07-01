package by.epam.likeit.service;

import by.epam.likeit.entity.User;
import by.epam.likeit.service.exception.ServiceException;

public interface QuestionService {
    /**
     * Add new question.
     * @param user Author of the question.
     * @param topic Topic of the question.
     * @param text Text of the question.
     * @throws ServiceException
     */
    void addQuestion(User user, String topic, String text) throws ServiceException;

    /**
     * Edit text of the question.
     * @param user User who edit question.
     * @param id Id of the question.
     * @param value New value for the question.
     * @throws ServiceException
     */
    void editQuestion(User user, int id, String value) throws ServiceException;

    /**
     * Delete question.
     * @param user User who delete question
     * @param id Id of the question.
     * @throws ServiceException
     */
    void deleteQuestion(User user, int id) throws ServiceException;
}
