package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public interface UserDAO {
    public User getUserById(int id);
}
