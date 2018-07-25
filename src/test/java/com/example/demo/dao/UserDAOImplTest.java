package com.example.demo.dao;

import com.example.demo.conf.MVCConfig;
import com.example.demo.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MVCConfig.class)
public class UserDAOImplTest {

    @Autowired
    UserDAO userDAO;

    User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @Test
    @Transactional
    public void getUserById() {
        user = userDAO.getUserById(-1);
        Assert.assertNull(user);
    }

    @Test
    public void addUser() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void removeUser() {
    }

    @Test
    public void getAllUsers() {
    }

    @Test
    public void addRole() {
    }
}