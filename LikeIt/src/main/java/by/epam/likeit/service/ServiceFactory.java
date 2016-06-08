package by.epam.likeit.service;

import by.epam.likeit.service.impl.*;


public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();
    private UserService userService;
    private AnswerService answerService;
    private QuestionService questionService;

    private ServiceFactory(){
        userService = new UserServiceImpl();
        answerService = new AnswerServiceImpl();
        questionService = new QuestionServiceImpl();
    }

    public static ServiceFactory getInstance(){
        return serviceFactory;
    }

    public UserService getUserService(){
        return userService;
    }

    public AnswerService getAnswerService(){
        return answerService;
    }

    public QuestionService getQuestionService(){
        return questionService;
    }
}
