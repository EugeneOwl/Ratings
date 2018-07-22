package com.example.demo.service;

import com.example.demo.DemoApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.dao.UserDAO;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public User getUserById(int id) {
        log.info("info");
        return userDAO.getUserById(id);
    }
}