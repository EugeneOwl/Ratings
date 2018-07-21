package com.example.demo;

import com.example.demo.config.AppConfig;
import com.example.demo.dao.UserDAO;
import com.example.demo.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//		ctx.register(AppConfig.class);
//		ctx.refresh();
//		UserDAO userDAO = ctx.getBean(UserDAO.class);
//
//		User user = new User();
//		user.setUsername("Eugene");
//
//		userDAO.saveUser(user);
//		System.out.println("Done");
	}
}
