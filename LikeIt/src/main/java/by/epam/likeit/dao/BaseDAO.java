package by.epam.likeit.dao;

import by.epam.likeit.dao.exception.DaoException;

import java.util.List;

/** BaseDAO interface determines the base operation with database (CRUD).
 * E - entity
 * K - key value in table
 * @author Anna Yakubenko
 * @author anechka396@mail.ru
 * @version 1.0
 */
public interface BaseDAO<E, K> {
    void create(E entity) throws DaoException;
    E retrieve(K id) throws DaoException;
    List<E> retrieveAll() throws DaoException;
    void update(E entity) throws DaoException;
    void delete(K id) throws DaoException;
}
