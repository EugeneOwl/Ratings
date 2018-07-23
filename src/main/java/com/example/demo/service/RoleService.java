package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;

import java.util.List;

public interface RoleService {

    Role getRoleById(int id);

    List<Role> getAllRoles();

    void addUser(Role role, User user);
}
