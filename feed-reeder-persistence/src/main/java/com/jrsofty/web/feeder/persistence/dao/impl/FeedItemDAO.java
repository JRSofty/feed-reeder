package com.jrsofty.web.feeder.persistence.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jrsofty.web.feeder.models.domain.FeedItem;

@Repository("FeedItem")
@Transactional
public class FeedItemDAO extends AbstractGenericDAO<FeedItem> {

    @Transactional
    @Override
    public void delete(long id) {
        final FeedItem inst = this.em.find(FeedItem.class, id);
        this.em.remove(inst);
    }

    @Transactional(readOnly = true)
    @Override
    public FeedItem findById(long id) {

        return this.em.find(FeedItem.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FeedItem> findAll() {
        final TypedQuery<FeedItem> query = this.em.createQuery("SELECT fi FROM FeedItem fi", FeedItem.class);
        return query.getResultList();
    }

}
