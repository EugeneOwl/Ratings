package com.example.demo.dao;

import com.example.demo.model.Role;
import com.example.demo.model.User;

import java.util.List;

public interface RoleDAO {

    Role getRoleById(int id);

    List<Role> getAllRoles();

    void addUser(Role role, User user);
}
