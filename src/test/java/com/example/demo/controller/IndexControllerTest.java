package com.example.demo.controller;

import com.example.demo.conf.MVCConfig;
import com.example.demo.model.User;
import com.example.demo.service.RawDataProcessor;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.hamcrest.Matchers;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.fail;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = MVCConfig.class)
public class IndexControllerTest {
    private MockMvc mockMvc;
    private User user;

    @Autowired
    private IndexController indexController;

    @MockBean
    private UserService userService;
    @MockBean
    private RoleService roleService;
    @MockBean
    private RawDataProcessor dataProcessor;

    @Before
    public void setup() {
        user = new User();
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(userService, roleService, dataProcessor);
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
        User user = new User();
        user.setUsername("test");
        List<User> users = Collections.singletonList(user);

        when(userService.getAllUsers()).thenReturn(users);

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

        verify(userService).removeUser(user.getId());
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

    @TestConfiguration
    public static class Config {
        @Bean
        public IndexController controller() {
            return new IndexController();
        }
    }
}