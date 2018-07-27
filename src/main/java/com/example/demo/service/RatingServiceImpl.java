package com.example.demo.service;

import com.example.demo.dto.RatingDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import com.example.demo.repository.RatingRepository;
import com.example.demo.transformer.RatingTransformer;
import com.example.demo.transformer.UserTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.MessageFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RatingTransformer ratingTransformer;

    @Autowired
    UserService userService;

    @Autowired
    private UserTransformer userTransformer;

    @Override
    public RatingDto getRatingById(int id) {
        if (ratingRepository.existsById(id)) {
            Rating rating = ratingRepository.getOne(id);
            log.info("Rating was taken by id: " + rating);

            return ratingTransformer.transform(rating);
        }
        log.info("Attempt to take not existing rating with id = {}", id);

        return null;
    }

    @Override
    public void addRating(RatingDto ratingDto) {
        Rating rating = ratingTransformer.transform(ratingDto);
        UserDto senderDto = getUserByRawId(ratingDto.getRawSender());
        UserDto recipientDto = getUserByRawId(ratingDto.getRawRecipient());
        rating.setRecipient(userTransformer.transform(recipientDto));
        rating.setSender(userTransformer.transform(senderDto));

        System.out.println("RATING TO SAVE: " + rating);
        System.out.println("SENDER: " + userTransformer.transform(senderDto));
        System.out.println("RECIPIENT: " + userTransformer.transform(recipientDto));
        if (isRatingValid(rating)) {
            ratingRepository.save(rating);
        }
        log.info("Rating was added: " + rating);
    }

    @Override
    public void removeRating(int id) {
        ratingRepository.deleteById(id);
        log.info(MessageFormat.format("Rating with id = {0} was removed: ", id));
    }

    @Override
    public List<RatingDto> getRatingsByRecipient(UserDto recipientDto) {
        User recipient = userTransformer.transform(recipientDto);
        List<Rating> list = ratingRepository.getRatingsByRecipient(recipient);
        for (Rating rating : list) {
            log.info(MessageFormat.format(
                    "Rating = {0} was taken by recipient = {1}",
                    rating,
                    recipient
                    ));
        }

        return list.stream()
                .map(ratingTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isRatingValid(Rating rating) {
        return (rating.getRecipient() != null &&
                rating.getSender() != null &&
                ! rating.getRecipient().equals(rating.getSender()) &&
                ! rating.getValue().trim().equals(""));
    }

    private UserDto getUserByRawId(String rawId) {
        return userService.getUserById(
                getNumeric(rawId)
        );
    }

    private int getNumeric(String rawNumber) {
        rawNumber = cleanNumericString(rawNumber);

        Scanner scanner = new Scanner(rawNumber);
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        return -1;
    }

    private String cleanNumericString(String numericString) {
        return numericString.replaceAll("[^0-9]", RawDataProcessor.delimiter);
    }

}
