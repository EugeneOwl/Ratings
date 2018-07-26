package com.example.demo.service;

import com.example.demo.conf.MVCConfig;
import com.example.demo.dao.RatingDAO;
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class RatingServiceImplTest {
    private Rating rating;

    @Autowired
    RatingService ratingService;
    @MockBean
    RatingDAO ratingDAO;

    @Before
    public void setUp() {
        rating = new Rating();
    }

    @After
    public void tearDown() {
        rating = null;
    }

    @Test
    public void isRatingValid() {
        User user = new User();

        rating.setValue("test value");
        rating.setRecipient(null);
        rating.setSender(user);
        Assert.assertFalse(ratingService.isRatingValid(rating));

        rating.setRecipient(user);
        rating.setSender(null);
        Assert.assertFalse(ratingService.isRatingValid(rating));

        rating.setRecipient(null);
        rating.setSender(null);
        Assert.assertFalse(ratingService.isRatingValid(rating));

        rating.setRecipient(user);
        rating.setSender(user);
        Assert.assertFalse(ratingService.isRatingValid(rating));

        User otherUser = new User();
        otherUser.setUsername("Other user");
        rating.setSender(user);
        rating.setRecipient(otherUser);
        rating.setValue("   ");
        Assert.assertFalse(ratingService.isRatingValid(rating));

        rating.setValue("test value");
        System.out.println(rating);
        Assert.assertTrue(ratingService.isRatingValid(rating));
    }

    @TestConfiguration
    public static class Config {
        @Bean
        public RatingService service() {
            return new RatingServiceImpl();
        }
    }
}