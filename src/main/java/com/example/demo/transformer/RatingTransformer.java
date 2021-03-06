package com.example.demo.transformer;

import com.example.demo.dto.RatingDto;
import com.example.demo.model.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingTransformer implements Transformer<Rating, RatingDto> {
    @Override
    public RatingDto transform(Rating rating) {
        RatingDto ratingDto =  RatingDto.builder()
                .value(rating.getValue())
                .rawRecipient("" + rating.getRecipient().getId())
                .rawSender("" + rating.getSender().getId())
                .sender(rating.getSender())
                .recipient(rating.getRecipient())
                .build();
        ratingDto.setId(rating.getId());

        return ratingDto;
    }

    @Override
    public Rating transform(RatingDto ratingDto) {
        Rating rating = Rating.builder()
                .value(ratingDto.getValue())
                .recipient(ratingDto.getRecipient())
                .sender(ratingDto.getSender())
                .build();
        rating.setId(ratingDto.getId());

        return rating;
    }
}
