package com.example.demo.dao;

import com.example.demo.conf.MVCConfig;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MVCConfig.class)
public class UserDAOImplTest {
    User user;

    @Autowired
    UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @After
    public void tearDown() {
        user = null;
    }

    private User getLastUser(List<User> users) {
        if (users.isEmpty()) {
            assert false : "can not test properly";
            return null;
        }
        return userDAO.getAllUsers().get(userDAO.getAllUsers().size() - 1);
    }

    @Test
    @Transactional
    public void getUserById() {
        user = userDAO.getUserById(0);
        Assert.assertNull(user);

        User lastUser = getLastUser(userDAO.getAllUsers());
        if (user == null) {
            return;
        }
        Assert.assertEquals(
                userDAO.getUserById(lastUser.getId()).getUsername(),
                lastUser.getUsername()
        );
        Assert.assertEquals(
                userDAO.getUserById(lastUser.getId()).getPassword(),
                lastUser.getPassword()
        );
    }

    @Test
    @Transactional
    public void addUser() {
        user.setUsername("test");
        user.setPassword("test");
        userDAO.addUser(user);

        User lastUser = getLastUser(userDAO.getAllUsers());
        Assert.assertEquals(
                lastUser.getUsername(),
                user.getUsername()
        );
        Assert.assertEquals(
                lastUser.getPassword(),
                user.getPassword()
        );
        userDAO.removeUser(lastUser.getId());
    }

    @Test
    @Transactional
    public void updateUser() {
        user = getLastUser(userDAO.getAllUsers());
        if (user == null) {
            return;
        }
        user.setPassword("New test password");
        user.setUsername("New test username");
        userDAO.updateUser(user);
        System.out.println(userDAO.getUserById(user.getId()));
        Assert.assertEquals(
                userDAO.getUserById(user.getId()).getPassword(),
                user.getPassword()
        );
        Assert.assertEquals(
                userDAO.getUserById(user.getId()).getUsername(),
                user.getUsername()
        );
    }

    @Test
    @Transactional
    public void removeUser() {
        user = getLastUser(userDAO.getAllUsers());
        if (user == null) {
            return;
        }
        userDAO.removeUser(user.getId());
        Assert.assertNull(userDAO.getUserById(user.getId()));
    }

    @Test
    @Transactional
    public void getAllUsers() {
        userDAO.addUser(new User(
                "test username",
                "test password",
                "",
                new HashSet<>())
        );
        Assert.assertTrue(! userDAO.getAllUsers().isEmpty());
    }

    @Test
    @Transactional
    public void addRole() {
        user = new User(
                "test username",
                "test password",
                "",
                new HashSet<>()
        );
        Role role = new Role("role value", new HashSet<>());
        userDAO.addRole(role, user);
        user = getLastUser(userDAO.getAllUsers());
        Assert.assertEquals(
                getLastUser(userDAO.getAllUsers()).getRoles().iterator().next(),
                user.getRoles().iterator().next()
        );
    }
}