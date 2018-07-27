package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
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
    RoleRepository roleRepository;

    @Override
    @Transactional
    public Role getRoleById(int id) {
        if (roleRepository.existsById(id)) {
            Role role = roleRepository.getOne(id);
            log.info("Role was taken by id: " + role);
            return role;
        }
        log.info("Attempt to take not existing role with id = {}", id);

        return null;
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        List<Role> list = roleRepository.findAll();
        for (Role role : list) {
            log.info("Role was taken: " + role);
        }

        return list;
    }

    @Override
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
