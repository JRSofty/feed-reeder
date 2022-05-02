package com.jrsofty.web.feeder.persistence.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jrsofty.web.feeder.models.domain.WebFeed;

@Repository("WebFeed")
@Transactional
public class WebFeedDAO extends AbstractGenericDAO<WebFeed> {

    @Transactional
    @Override
    public void delete(long id) {
        final WebFeed inst = this.em.find(WebFeed.class, id);
        this.em.remove(inst);
    }

    @Transactional(readOnly = true)
    @Override
    public WebFeed findById(long id) {

        return this.em.find(WebFeed.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<WebFeed> findAll() {
        final TypedQuery<WebFeed> query = this.em.createQuery("SELECT wf FROM WebFeed wf", WebFeed.class);
        return query.getResultList();
    }

    public boolean alreadyExists(WebFeed feed) {
        final TypedQuery<Long> query = this.em.createQuery("SELECT COUNT(wf) FROM WebFeed wf WHERE wf.title = :title AND wf.feedUrl = :url", Long.class);
        query.setParameter("title", feed.getTitle());
        query.setParameter("url", feed.getFeedUrl());
        final Long cnt = query.getSingleResult();
        boolean result = false;
        if (cnt != 0) {
            result = true;
        }
        return result;
    }

}
