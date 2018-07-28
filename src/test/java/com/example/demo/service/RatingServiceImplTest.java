package com.example.demo.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RatingServiceImplTest {
//    private Rating rating;
//
//    @Autowired
//    RatingService ratingService;
//    @MockBean
//    RatingDAO ratingDAO;
//
//    @Before
//    public void setUp() {
//        rating = new Rating();
//    }
//
//    @After
//    public void tearDown() {
//        rating = null;
//    }
//
//    @Test
//    public void isRatingValid() {
//        User user = new User();
//
//        rating.setValue("test value");
//        rating.setRecipient(null);
//        rating.setSender(user);
//        Assert.assertFalse(ratingService.isRatingValid(rating));
//
//        rating.setRecipient(user);
//        rating.setSender(null);
//        Assert.assertFalse(ratingService.isRatingValid(rating));
//
//        rating.setRecipient(null);
//        rating.setSender(null);
//        Assert.assertFalse(ratingService.isRatingValid(rating));
//
//        rating.setRecipient(user);
//        rating.setSender(user);
//        Assert.assertFalse(ratingService.isRatingValid(rating));
//
//        User otherUser = new User();
//        otherUser.setUsername("Other user");
//        rating.setSender(user);
//        rating.setRecipient(otherUser);
//        rating.setValue("   ");
//        Assert.assertFalse(ratingService.isRatingValid(rating));
//
//        rating.setValue("test value");
//        System.out.println(rating);
//        Assert.assertTrue(ratingService.isRatingValid(rating));
//    }
//
//    @TestConfiguration
//    public static class Config {
//        @Bean
//        public RatingService service() {
//            return new RatingServiceImpl();
//        }
//    }
}