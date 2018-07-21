package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @Autowired
    DataSource dataSource;
//
//    @Autowired
//    SessionFactory sessionFactory;

    @RequestMapping("/")
    public String index(Model model) throws SQLException {
        model.addAttribute("user", userService.getUserById(1));


        Statement statement = dataSource.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("username"));
        }

//        sessionFactory.getCurrentSession();

        return "index";
    }
}
