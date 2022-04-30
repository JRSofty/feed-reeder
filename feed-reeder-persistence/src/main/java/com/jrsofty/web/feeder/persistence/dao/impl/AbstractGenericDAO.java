package com.jrsofty.web.feeder.persistence.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jrsofty.web.feeder.persistence.dao.GenericDAO;

public abstract class AbstractGenericDAO<T> implements GenericDAO<T> {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public T store(T instance) {
        return this.em.merge(instance);
    }

    @Override
    public abstract void delete(long id);

    @Override
    public abstract T findById(long id);

    @Override
    public abstract List<T> findAll();

}
