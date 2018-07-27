package com.example.demo.controller;

import com.example.demo.model.Rating;
import com.example.demo.model.User;
import com.example.demo.service.RatingService;
import com.example.demo.service.RawDataProcessor;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ratings")
public class RatingsController {

    @Autowired
    UserService userService;

    @Autowired
    RatingService ratingService;

    @Autowired
    RawDataProcessor dataProcessor;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("rating", new Rating());

        return "rating";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addRating(@ModelAttribute("rating") Rating rating) {
//        User recipient = userService.getUserById(
//            dataProcessor.getNumeric(rating.getRawRecipient())
//        );
//        User sender = userService.getUserById(
//                dataProcessor.getNumeric(rating.getRawSender())
//        );
//        rating.setRecipient(recipient);
//        rating.setSender(sender);
        rating.setValue(rating.getValue().trim());
        if (ratingService.isRatingValid(rating)) {
            ratingService.addRating(rating);
        }

        return "redirect:/ratings";
    }

    @RequestMapping("/user/{id}")
    public String ratingData(@PathVariable("id") int userId, Model model) {
        //User recipient = userService.getUserById(userId);
//        model.addAttribute("recipient", recipient);
//        model.addAttribute("ratings",
//                ratingService.getRatingsByRecipient(recipient));

        return "ratingdata";
    }

    @RequestMapping("/remove/{id}")
    public String removeRating(@PathVariable("id") int ratingId) {
        int userId = ratingService.getRatingById(ratingId).getRecipient().getId();
        ratingService.removeRating(ratingId);

        return "redirect:/ratings/user/" + userId;
    }
}
