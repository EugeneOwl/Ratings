package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public User getUserById(int id) {
        User user = userDAO.getUserById(id);
        log.info("User was taken by id: " + user);

        return user;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
        log.info("User was added: " + user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDAO.updateUser(user);
        log.info("User was updated: " + user);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        userDAO.removeUser(id);
        log.info(MessageFormat.format("User with id = {0} was removed: ", id));
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        List<User> list = userDAO.getAllUsers();
        for (User user : list) {
            log.info("User was taken: " + user);
        }

        return list;
    }

    @Override
    @Transactional
    public void addRole(Role role, User user) {
        userDAO.addRole(role, user);
        log.info("User got new role: " + user + "; " + role);
    }

    @Override
    public boolean isUserValid(User user) {
        return (
                user != null &&
                user.getUsername().trim().length() != 0 &&
                user.getPassword().trim().length() != 0
                );
    }
}
