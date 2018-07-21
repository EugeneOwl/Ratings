package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    UserService userService = new UserServiceImpl();

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("user", userService.getUserById(1));
        return "index";
    }
}
