package by.epam.likeit.service;

import by.epam.likeit.service.impl.*;

/**
 * Returns the special Service.
 * @author Anna Yakubenko
 * @author anechka396@mail.ru
 * @version 1.0
 */

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();
    private UserService userService;
    private AnswerService answerService;
    private QuestionService questionService;
    private TopicService topicService;

    private ServiceFactory(){
        userService = new UserServiceImpl();
        answerService = new AnswerServiceImpl();
        questionService = new QuestionServiceImpl();
        topicService = new TopicServiceImpl();
    }

    /**
     * Returns the instance of factory.
     * @return
     */
    public static ServiceFactory getInstance(){
        return serviceFactory;
    }

    /**
     * Returns user service.
     * @return
     */
    public UserService getUserService(){
        return userService;
    }

    /**
     * Returns answer service.
     * @return
     */
    public AnswerService getAnswerService(){
        return answerService;
    }

    /**
     * Returns question service.
     * @return
     */
    public QuestionService getQuestionService(){
        return questionService;
    }

    /**
     * Returns topic service.
     * @return
     */
    public TopicService getTopicService(){
        return topicService;
    }
}
