package com.example.demo.dao;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Role getRoleById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Role.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getAllRoles() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from Role").list();
    }

    @Override
    public void addUser(Role role, User user) {
        Session session = sessionFactory.getCurrentSession();
        role.addUser(user);
        session.save(role);
    }
}
