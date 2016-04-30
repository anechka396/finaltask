package by.epam.likeit.dao;

import by.epam.likeit.entity.User;

import java.util.List;

public interface UserDAO extends BaseDAO<User, String> {
    public User retrieveUserByEmail(String email);
}
