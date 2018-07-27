package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("userAction", "Add");

        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") UserDto userDto){
        userService.addOrUpdateUserIfValid(userDto);

        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id){
        userService.removeUser(id);

        return "redirect:/users";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("userAction", "Edit");

        return "index";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.getUserById(id));

        return "userdata";
    }
}
