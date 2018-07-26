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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RatingDAOImplTest {

    @Autowired
    private TestEntityManager testEntityManager;

    private Rating rating;

    @Autowired
    private RatingDAO ratingDAO;

    @Before
    public void setUp() throws Exception {
        rating = new Rating();
    }

    @After
    public void tearDown() throws Exception {
        rating = null;
    }

    private Rating getLastRating() {
        Rating rating = new Rating();
        testEntityManager.persist(rating);
        return rating;
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