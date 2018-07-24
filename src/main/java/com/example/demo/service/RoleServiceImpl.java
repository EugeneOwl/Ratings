package com.example.demo.service;

import com.example.demo.dao.RoleDAO;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDAO roleDAO;

    @Override
    @Transactional
    public Role getRoleById(int id) {
        Role role = roleDAO.getRoleById(id);
        log.info("Role was taken by id: " + role);

        return role;
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        List<Role> list = roleDAO.getAllRoles();
        for (Role role : list) {
            log.info("Role was taken: " + role);
        }

        return list;
    }

    @Override
    @Transactional
    public void addUser(Role role, User user) {
        roleDAO.addUser(role, user);
        log.info("Role got new user: " + user + "; " + role);
    }

    @Override
    @Transactional
    public List<Role> getRoleListByIds(List<Integer> ids) {
        List<Role> roles = new ArrayList<>();
        for (Integer id : ids) {
            Role role = getRoleById(id);
            if (role != null) {
                roles.add(role);
            }
        }
        return roles;
    }
}
