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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MVCConfig.class)
public class RoleDAOImplTest {
    private Role role;

    @Autowired
    private RoleDAO roleDAO;

    @Before
    public void setUp() throws Exception {
        role = new Role();
    }

    @After
    public void tearDown() {
        role = null;
    }

    private Role getLastRole(List<Role> roles) {
        if (roles.isEmpty()) {
            assert false : "can not test properly";
            return null;
        }
        return roles.get(roles.size() - 1);
    }

    @Test
    @Transactional
    public void getRoleById() {
        role = roleDAO.getRoleById(0);
        Assert.assertNull(role);

        Role lastRole = getLastRole(roleDAO.getAllRoles());
        if (lastRole == null) {
            return;
        }
        Assert.assertEquals(
                roleDAO.getRoleById(lastRole.getId()).getValue(),
                lastRole.getValue()
        );
    }

    @Test
    @Transactional
    public void getAllRoles() {
        // adding not implemented. Only manually nia DB
    }

    @Test
    @Transactional
    public void addUser() {
        role = getLastRole(roleDAO.getAllRoles());

        User user = new User(
                "test username",
                "test password",
                "",
                new HashSet<>()
        );

        roleDAO.addUser(role, user);

        Assert.assertEquals(
                getLastRole(roleDAO.getAllRoles()).getUsers().iterator().next(),
                user
        );
    }
}