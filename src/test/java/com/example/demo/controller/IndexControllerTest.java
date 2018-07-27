package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = IndexController.class, secure = false)
@RunWith(SpringRunner.class)
@EnableSpringDataWebSupport
public class IndexControllerTest {
    private UserDto userDto;

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    RoleService roleService;

    @Before
    public void setup() {
        userDto = new UserDto();
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(userService, roleService);
        userDto = null;
    }

    @Test
    public void index() throws Exception {
        RequestBuilder request = get("/users");

        userDto.setUsername("test username");
        List<UserDto> userDtoList = Collections.singletonList(userDto);

        when(userService.getAllUsers()).thenReturn(userDtoList);

        mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("userAction", "Add"))
        ;
    }
//
//    @Test
//    public void addUser() throws Exception{
//        user.setPassword("test password");
//        user.setUsername("Test Name");
//        RequestBuilder request = post("/users/add")
//                .param("username", user.getUsername())
//                .param("password", user.getPassword())
//                .param("rawRoles", "")
//                ;
//
//        mockMvc.perform(request)
//                .andExpect(redirectedUrl("/users"));
//        List<User> users = userService.getAllUsers();
//        if (users.isEmpty()) {
//            fail("User list can not be empty after adding one.");
//            return;
//        }
//
//        Assert.assertEquals(
//                users.get(users.size() - 1).getUsername(),
//                user.getUsername()
//        );
//        Assert.assertEquals(
//                users.get(users.size() - 1).getPassword(),
//                user.getPassword()
//        );
//    }
//
//    @Test
//    public void removeUser() throws Exception {
//        user = getFirstUserFromDatabase();
//        if (user == null) {
//            return;
//        }
//        RequestBuilder request = get("/users/remove/" + user.getId());
//
//        mockMvc.perform(request)
//                .andExpect(redirectedUrl("/users"));
//        Assert.assertNull(userService.getUserById(user.getId()));
//
//        verify(userService).removeUser(user.getId());
//    }
//
//    @Test
//    public void editUser() throws Exception {
//        user = getFirstUserFromDatabase();
//        if (user == null) {
//            return;
//        }
//        RequestBuilder request = get("/users/edit/" + user.getId());
//
//        mockMvc.perform(request)
//                .andExpect(model().attribute(
//                        "user",
//                        Matchers.hasProperty(
//                                "username",
//                                Matchers.equalTo(user.getUsername())
//                        )
//                ))
//                .andExpect(model().attribute(
//                        "user",
//                        Matchers.hasProperty(
//                                "password",
//                                Matchers.equalTo(user.getPassword())
//                        )
//                ))
//                .andExpect(model().attribute("userAction", "Edit"))
//        ;
//    }
//
//    @Test
//    public void userData() throws Exception {
//        user = getFirstUserFromDatabase();
//        if (user == null) {
//            return;
//        }
//        mockMvc.perform(post("/users/userdata/" + user.getId()))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("user"))
//                .andExpect(model().attribute(
//                        "user",
//                        Matchers.hasProperty(
//                                "username",
//                                Matchers.equalTo(user.getUsername())
//                        )
//                ))
//                .andExpect(model().attribute(
//                        "user",
//                        Matchers.hasProperty(
//                                "password",
//                                Matchers.equalTo(user.getPassword())
//                        )
//                ))
//        ;
//    }

    @TestConfiguration
    public static class Config {
        @Bean
        public IndexController controller() {
            return new IndexController();
        }
    }
}