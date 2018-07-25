package com.example.demo.controller;

import com.example.demo.conf.MVCConfig;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.hamcrest.Matchers;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.fail;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MVCConfig.class)
public class IndexControllerTest {
    private MockMvc mockMvc;
    private User user;

    @Autowired
    private IndexController indexController;

    @Autowired
    private UserService userService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        user = new User();
    }

    @After
    public void tearDown() {
        user = null;
    }

    private User getFirstUserFromDatabase() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Test
    public void index() throws Exception {
        RequestBuilder request = get("/users");

        mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("userAction", "Add"))
                .andExpect(model().attributeExists("users"))
        ;
    }

    @Test
    @Transactional
    public void addUser() throws Exception{
        user.setPassword("test password");
        user.setUsername("Test Name");
        RequestBuilder request = post("/users/add")
                .param("username", user.getUsername())
                .param("password", user.getPassword())
                .param("rawRoles", "")
                ;

        mockMvc.perform(request)
                .andExpect(redirectedUrl("/users"));
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            fail("User list can not be empty after adding one.");
            return;
        }

        Assert.assertEquals(
                users.get(users.size() - 1).getUsername(),
                user.getUsername()
        );
        Assert.assertEquals(
                users.get(users.size() - 1).getPassword(),
                user.getPassword()
        );
    }

    @Test
    @Transactional
    public void removeUser() throws Exception {
        user = getFirstUserFromDatabase();
        if (user == null) {
            return;
        }
        RequestBuilder request = get("/users/remove/" + user.getId());

        mockMvc.perform(request)
                .andExpect(redirectedUrl("/users"));
        Assert.assertNull(userService.getUserById(user.getId()));
    }

    @Test
    public void editUser() throws Exception {
        user = getFirstUserFromDatabase();
        if (user == null) {
            return;
        }
        RequestBuilder request = get("/users/edit/" + user.getId());

        mockMvc.perform(request)
                .andExpect(model().attribute(
                        "user",
                        Matchers.hasProperty(
                                "username",
                                Matchers.equalTo(user.getUsername())
                        )
                ))
                .andExpect(model().attribute(
                        "user",
                        Matchers.hasProperty(
                                "password",
                                Matchers.equalTo(user.getPassword())
                        )
                ))
                .andExpect(model().attribute("userAction", "Edit"))
        ;
    }

    @Test
    public void userData() throws Exception {
        user = getFirstUserFromDatabase();
        if (user == null) {
            return;
        }
        mockMvc.perform(post("/users/userdata/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute(
                        "user",
                        Matchers.hasProperty(
                                "username",
                                Matchers.equalTo(user.getUsername())
                        )
                ))
                .andExpect(model().attribute(
                        "user",
                        Matchers.hasProperty(
                                "password",
                                Matchers.equalTo(user.getPassword())
                        )
                ))
        ;
    }
}