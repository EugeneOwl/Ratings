package com.example.demo.dao;

import com.example.demo.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User getUserById(int id) {
        User user = new User();
        user.setUsername("User not from Database.");
        sessionFactory.getCurrentSession();
        return user;
    }
}