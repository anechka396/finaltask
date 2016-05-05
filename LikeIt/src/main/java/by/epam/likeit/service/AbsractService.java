package by.epam.likeit.service;

import by.epam.likeit.service.exception.ServiceException;

import java.util.List;

public abstract class AbsractService<E>{

    public List<E> service(int param1, String param2, String param3) throws ServiceException{
        return null;
    }

    public void service(E entity, String param1, String param2) throws ServiceException {}

    public E service(String param1, String param2) throws ServiceException {
        return null;
    }

    public E service(String param1, String param2, String param3, String param4, String param5) throws ServiceException {
        return null;
    }
}
