package com.example.demo.service;

import com.example.demo.dto.RatingDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Rating;

import java.util.List;

public interface RatingService {

    RatingDto getRatingById(int id);

    void addRating(RatingDto ratingDto);

    void removeRating(int id);

    List<RatingDto> getRatingsByRecipient(UserDto recipient);

    boolean isRatingValid(Rating rating);
}
