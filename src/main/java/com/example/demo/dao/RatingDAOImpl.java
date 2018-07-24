package com.example.demo.dao;

import com.example.demo.model.Rating;
import com.example.demo.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;

@Repository
public class RatingDAOImpl implements RatingDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Rating getRatingById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Rating.class, id);
    }

    @Override
    public void addRating(Rating rating) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(rating);
    }

    @Override
    public void removeRating(int id) {
        Session session = sessionFactory.getCurrentSession();
        Rating rating = session.load(Rating.class, id);

        if (rating != null) {
            session.delete(rating);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Rating> getRatingsByRecipient(User recipient) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(
                MessageFormat.format(
                        "from Rating R where R.recipient = {0}",
                        recipient.getId()
                )
        ).list();
    }
}
