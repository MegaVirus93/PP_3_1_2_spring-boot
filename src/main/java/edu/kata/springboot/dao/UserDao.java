package edu.kata.springboot.dao;

import edu.kata.springboot.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> listUsers();

    User getUserById(long id);

    void update(User user, long id);

    void delete(long id);
}
