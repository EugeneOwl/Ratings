package com.example.demo.dao;

import com.example.demo.model.User;

import java.util.List;

public interface UserDAO {

    User getUserById(int id);

    void addUser(User user);

    void updateUser(User user);

    void removeUser(int id);

    List<User> getAllUsers();
}
