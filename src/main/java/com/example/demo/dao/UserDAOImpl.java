package com.example.demo.dao;

import com.example.demo.model.Rating;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
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
        session.saveOrUpdate(user);
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void removeUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);

        if (user != null) {
            List<Rating> ratings = session.createQuery(
                    MessageFormat.format(
                            "from Rating R WHERE R.recipient = {0} OR R.sender = {0}",
                            id)
            ).list();
            for (Rating rating : ratings) {
                session.delete(rating);
            }
            session.delete(user);
            session.flush();

        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from User order by id ASC").list();
    }

    @Override
    public void addRole(Role role, User user) {
        Session session = sessionFactory.getCurrentSession();
        user.addRole(role);
        session.saveOrUpdate(user);
    }
}
