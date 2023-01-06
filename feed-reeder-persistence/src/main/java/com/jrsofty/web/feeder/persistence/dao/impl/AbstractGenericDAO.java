package com.jrsofty.web.feeder.persistence.dao.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jrsofty.web.feeder.persistence.dao.GenericDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Transactional
public abstract class AbstractGenericDAO<T> implements GenericDAO<T> {

    @PersistenceContext
    protected EntityManager em;

    @Transactional
    @Override
    public T store(final T instance) {
        return this.em.merge(instance);
    }

    @Override
    public abstract void delete(long id);

    @Override
    public abstract T findById(long id);

    @Override
    public abstract List<T> findAll();

}
