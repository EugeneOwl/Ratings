package com.example.demo.dao;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public void removeUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);

        if(user != null){
            session.delete(user);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from User").list();
    }

    @Override
    public void addRole(Role role, User user) {
        Session session = sessionFactory.getCurrentSession();
//        user.addRole(role);
//        session.persist(user);
        Transaction transaction = session.getTransaction();

        Set<Role> roles = new HashSet<Role>();
        roles.add(new Role("Maths", new HashSet<>()));
        roles.add(new Role("Maths1", new HashSet<>()));

        User user1 = new User("Eswar", "s", roles);
        User user2 = new User("Joe", "k", roles);
        session.save(user1);
        session.save(user2);

        //transaction.commit();
    }
}
