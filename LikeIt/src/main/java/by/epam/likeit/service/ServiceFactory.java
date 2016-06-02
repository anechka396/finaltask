package by.epam.likeit.service;

import by.epam.likeit.service.impl.*;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();
    private Map<ServiceName, Service> services = new HashMap<>();

    private ServiceFactory(){
        services.put(ServiceName.LOGIN, new LoginService());
        services.put(ServiceName.ADD_ANSWER, new AddAnswerService());
        services.put(ServiceName.ADD_QUESTION, new AddQuestionService());
        services.put(ServiceName.EDIT_QUESTION, new EditQuestionService());
        services.put(ServiceName.SET_RATING, new SetRatingService());
        services.put(ServiceName.REGISTRATION, new RegistrationService());
    }

    public static ServiceFactory getInstance(){
        return serviceFactory;
    }

    public Service getService(ServiceName serviceName){
        return services.get(serviceName);
    }
}
