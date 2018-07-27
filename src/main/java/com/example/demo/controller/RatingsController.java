package com.example.demo.controller;

import com.example.demo.dto.RatingDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.RatingService;
import com.example.demo.service.RawDataProcessor;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ratings")
public class RatingsController {

    @Autowired
    UserService userService;

    @Autowired
    RatingService ratingService;

    @Autowired
    RawDataProcessor dataProcessor;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("rating", new RatingDto());

        return "rating";
    }

    @PostMapping(value = "/add")
    public String addRating(@ModelAttribute("rating") RatingDto ratingDto) {
        ratingService.addRating(ratingDto);
        return "redirect:/ratings";
    }

    @GetMapping("/user/{id}")
    public String ratingData(@PathVariable("id") int userId, Model model) {
        UserDto recipientDto = userService.getUserById(userId);
        model.addAttribute("recipient", recipientDto);
        model.addAttribute("ratings",
                ratingService.getRatingsByRecipient(recipientDto));

        return "ratingdata";
    }

    @GetMapping("/remove/{id}")
    public String removeRating(@PathVariable("id") int ratingId) {
        int userId = ratingService.getRatingById(ratingId).getRecipient().getId();
        ratingService.removeRating(ratingId);

        return "redirect:/ratings/user/" + userId;
    }
}
