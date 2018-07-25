package com.example.demo.dao;

import com.example.demo.conf.MVCConfig;
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MVCConfig.class)
public class RatingDAOImplTest {
    private Rating rating;

    @Autowired
    private RatingDAO ratingDAO;

    @Autowired
    UserService userService;

    @Before
    public void setUp() throws Exception {
        rating = new Rating();
    }

    @After
    public void tearDown() throws Exception {
        rating = null;
    }

    private Rating getLastRating() {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            Rating rating = ratingDAO.getRatingsByRecipient(user).get(0);
            if (rating != null) {
                return rating;
            }
        }
        return null;
    }

    @Test
    @Transactional
    public void getRatingById() {
        rating = ratingDAO.getRatingById(0);
        Assert.assertNull(rating);

        Rating lastRating = getLastRating();
        if (lastRating == null) {
            return;
        }
        Assert.assertEquals(
                ratingDAO.getRatingById(lastRating.getId()).getValue(),
                lastRating.getValue()
        );
    }

    @Test
    @Transactional
    public void addRating() {
        rating = getLastRating();
        ratingDAO.removeRating(rating.getId());
        ratingDAO.addRating(rating);
        Assert.assertEquals(
                ratingDAO.getRatingById(rating.getId()).getValue(),
                rating.getValue()
        );
    }

    @Test
    @Transactional
    public void removeRating() {
        rating = getLastRating();
        ratingDAO.removeRating(rating.getId());
        Assert.assertNull(ratingDAO.getRatingById(rating.getId()));
    }

    @Test
    @Transactional
    public void getRatingsByRecipient() {
        rating = getLastRating();
        List<Rating> ratings = ratingDAO.getRatingsByRecipient(rating.getRecipient());
        Assert.assertTrue(ratings.contains(rating));
    }
}