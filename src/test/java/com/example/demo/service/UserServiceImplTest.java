package com.example.demo.service;

import com.example.demo.conf.MVCConfig;
import com.example.demo.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MVCConfig.class)
public class UserServiceImplTest {
    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Autowired
    UserService userService;

    @Test
    public void isUserValid() {
//        user = null;
//        Assert.assertFalse(userService.isUserValid(user));
//
//        user = new User();
//        user.setUsername("");
//        user.setPassword("test password");
//        Assert.assertFalse(userService.isUserValid(user));
//
//        user.setUsername("test username");
//        user.setPassword("");
//        Assert.assertFalse(userService.isUserValid(user));
//
//        user.setUsername("  ");
//        user.setPassword("      ");
//        Assert.assertFalse(userService.isUserValid(user));
//
//        user.setUsername("test username");
//        user.setPassword("test password");
//        Assert.assertTrue(userService.isUserValid(user));
    }
}