package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
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
    UserRepository userRepository;

    @Override
    public User getUserById(int id) {
        if (userRepository.existsById(id)) {
            User user = userRepository.getOne(id);
            log.info("User was taken by id: " + user);
            return user;
        }
        log.info("Attempt to take not existing user with id = {}", id);

        return null;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
        log.info("User was added: " + user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
        log.info("User was updated: " + user);
    }

    @Override
    public void removeUser(int id) {
        userRepository.deleteById(id);
        log.info(MessageFormat.format("User with id = {0} was removed: ", id));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = userRepository.findAll();
        for (User user : list) {
            log.info("User was taken: " + user);
        }

        return list;
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
