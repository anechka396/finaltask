package by.epam.likeit.dao;

import by.epam.likeit.dao.exception.DaoException;

import java.util.List;

/**
 * Created by Пользователь on 19.04.2016.
 */
public interface BaseDAO<E, K> {
    public void create(E entity) throws DaoException;
    public E retrieve(K id) throws DaoException;
    public List<E> retrieveAll() throws DaoException;
    public void update(E entity);
    public void delete(K id);
}
