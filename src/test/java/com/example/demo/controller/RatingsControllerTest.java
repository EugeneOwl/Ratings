package com.example.demo.controller;

import com.example.demo.conf.MVCConfig;
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import com.example.demo.service.RatingService;
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

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MVCConfig.class)
public class RatingsControllerTest {
//    private MockMvc mockMvc;
//    private Rating rating;
//    private User user;
//
//    @Autowired
//    RatingsController ratingsController;
//
//    @Autowired
//    RatingService ratingService;
//
//    @Autowired
//    UserService userService;
//
//    @Before
//    public void setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(ratingsController).build();
//        rating = new Rating();
//        user = new User();
//    }
//
//    @After
//    public void tearDown() {
//        rating = null;
//        user = null;
//    }
//
//    private User getFirstUserFromDatabase() {
//        List<User> users = userService.getAllUsers();
//        if (users.isEmpty()) {
//            return null;
//        }
//        return users.get(0);
//    }
//
//    private Rating getFirstRatingFromDatabase() {
//        List<User> users = userService.getAllUsers();
//        for (User user : users) {
//            List<Rating> ratings = ratingService.getRatingsByRecipient(user);
//            if (! ratings.isEmpty()) {
//                return ratings.get(0);
//            }
//        }
//        return null;
//    }
//
//    private User getLastUserFromDatabase() {
//        List<User> users = userService.getAllUsers();
//        if (users.isEmpty()) {
//            return null;
//        }
//        return users.get(users.size() - 1);
//    }
//
//    @Test
//    public void userList() throws Exception {
//        RequestBuilder request = get("/ratings");
//        mockMvc
//                .perform(request)
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("rating"))
//                .andExpect(model().attributeExists("rating"))
//                .andExpect(model().attributeExists("users"))
//        ;
//    }
//
//    @Test
//    public void addRating() throws Exception {
//        User sender = new User();
//        sender.setUsername("Sender test");
//        sender.setPassword("password test");
//        userService.addUser(sender);
//        sender = getLastUserFromDatabase();
//        Assert.assertNotNull(sender);
//
//        User recipient = new User();
//        recipient.setUsername("Recipient test");
//        recipient.setPassword("password test");
//        userService.addUser(recipient);
//        recipient = getLastUserFromDatabase();
//        Assert.assertNotNull(recipient);
//
//        rating.setValue("Test value");
//        rating.setRawSender("" + sender.getId());
//        rating.setRawRecipient("" + recipient.getId());
//
//        RequestBuilder request = post("/ratings/add")
//                .param("value", rating.getValue())
//                .param("rawSender", rating.getRawSender())
//                .param("rawRecipient", rating.getRawRecipient())
//                ;
//
//        mockMvc.perform(request)
//                .andExpect(redirectedUrl("/ratings"));
//
//
//        Assert.assertEquals(
//                ratingService.getRatingsByRecipient(recipient).get(0).getRecipient().getUsername(),
//                recipient.getUsername()
//        );
//        userService.removeUser(sender.getId());
//        userService.removeUser(recipient.getId());
//    }
//
//    @Test
//    public void ratingData() throws Exception {
//        user = getFirstUserFromDatabase();
//        if (user == null) {
//            return;
//        }
//
//        mockMvc.perform(post("/ratings/user/" + user.getId()))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("recipient"))
//                .andExpect(model().attribute(
//                        "recipient",
//                        Matchers.hasProperty(
//                                "username",
//                                Matchers.equalTo(user.getUsername())
//                        )
//                ))
//                .andExpect(model().attribute(
//                        "recipient",
//                        Matchers.hasProperty(
//                                "password",
//                                Matchers.equalTo(user.getPassword())
//                        )
//                ))
//                .andExpect(model().attributeExists("ratings"))
//        ;
//    }
//
//    @Test
//    @Transactional
//    public void removeRating() throws Exception {
//        rating = getFirstRatingFromDatabase();
//        if (rating == null) {
//            return;
//        }
//        RequestBuilder request = get("/ratings/remove/" + rating.getId());
//
//        mockMvc.perform(request)
//                .andExpect(redirectedUrl(
//                        "/ratings/user/" + rating.getRecipient().getId())
//                );
//        Assert.assertNull(ratingService.getRatingById(rating.getId()));
//    }
}