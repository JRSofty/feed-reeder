package com.jrsofty.web.feeder.persistence.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jrsofty.web.feeder.models.domain.WebFeed;

@Repository("WebFeed")
public class WebFeedDAO extends AbstractGenericDAO<WebFeed> {

    @Override
    public void delete(long id) {
        final WebFeed inst = this.em.find(WebFeed.class, id);
        this.em.remove(inst);
    }

    @Override
    public WebFeed findById(long id) {

        return this.em.find(WebFeed.class, id);
    }

    @Override
    public List<WebFeed> findAll() {
        final TypedQuery<WebFeed> query = this.em.createQuery("SELECT wf FROM WebFeed wf", WebFeed.class);
        return query.getResultList();
    }

}
