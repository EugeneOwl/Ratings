package com.example.demo.service;

import com.example.demo.dao.RatingDAO;
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Slf4j
@Service
public class RatingServiceImpl implements RatingDAO {

    @Autowired
    RatingDAO ratingDAO;

    @Override
    @Transactional
    public Rating getRatingById(int id) {
        Rating rating = ratingDAO.getRatingById(id);
        log.info("Rating was taken by id: " + rating);

        return rating;
    }

    @Override
    @Transactional
    public void addRating(Rating rating) {
        ratingDAO.addRating(rating);
        log.info("Rating was added: " + rating);
    }

    @Override
    @Transactional
    public void removeRating(int id) {
        ratingDAO.removeRating(id);
        log.info(MessageFormat.format("Rating with id = {0} was removed: ", id));
    }

    @Override
    @Transactional
    public List<Rating> getRatingsByRecipient(User recipient) {
        List<Rating> list = ratingDAO.getRatingsByRecipient(recipient);
        for (Rating rating : list) {
            log.info(MessageFormat.format(
                    "Rating = {0} was taken by recipient = {1}",
                    rating,
                    recipient
                    ));
        }

        return list;
    }
}
