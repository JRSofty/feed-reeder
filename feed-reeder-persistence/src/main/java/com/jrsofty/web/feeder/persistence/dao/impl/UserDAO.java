package com.jrsofty.web.feeder.persistence.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jrsofty.web.feeder.models.domain.User;

import jakarta.persistence.TypedQuery;

@Repository("User")
@Transactional
public class UserDAO extends AbstractGenericDAO<User> {

    @Override
    @Transactional
    public void delete(final long id) {
        final User user = this.em.find(User.class, id);
        this.em.remove(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(final long id) {
        return this.em.find(User.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        final TypedQuery<User> query = this.em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public long countUsers() {
        final TypedQuery<Long> query = this.em.createQuery("SELECT COUNT(*) FROM User", Long.class);
        return query.getSingleResult();
    }

}
