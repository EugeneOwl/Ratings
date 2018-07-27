package com.example.demo.service;

import com.example.demo.model.Rating;
import com.example.demo.model.User;
import com.example.demo.repository.RatingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.MessageFormat;
import java.util.List;

@Slf4j
@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating getRatingById(int id) {
        Rating rating = ratingRepository.getOne(id);
        log.info("Rating was taken by id: " + rating);

        return rating;
    }

    @Override
    public void addRating(Rating rating) {
        ratingRepository.save(rating);
        log.info("Rating was added: " + rating);
    }

    @Override
    public void removeRating(int id) {
        ratingRepository.deleteById(id);
        log.info(MessageFormat.format("Rating with id = {0} was removed: ", id));
    }

    @Override
    public List<Rating> getRatingsByRecipient(User recipient) {
        List<Rating> list = ratingRepository.getRatingsByRecipient(recipient);
        for (Rating rating : list) {
            log.info(MessageFormat.format(
                    "Rating = {0} was taken by recipient = {1}",
                    rating,
                    recipient
                    ));
        }

        return list;
    }

    @Override
    public boolean isRatingValid(Rating rating) {
        return (rating.getRecipient() != null &&
                rating.getSender() != null &&
                ! rating.getRecipient().equals(rating.getSender()) &&
                ! rating.getValue().trim().equals(""));
    }
}
