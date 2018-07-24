package com.example.demo.service;

import com.example.demo.model.Rating;
import com.example.demo.model.User;

import java.util.List;

public interface RatingService {

    Rating getRatingById(int id);

    void addRating(Rating rating);

    void removeRating(int id);

    List<Rating> getRatingsByRecipient(User recipient);
}
