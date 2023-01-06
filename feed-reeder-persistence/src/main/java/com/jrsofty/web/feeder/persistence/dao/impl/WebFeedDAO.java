package com.jrsofty.web.feeder.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jrsofty.web.feeder.models.domain.WebFeed;
import com.jrsofty.web.feeder.models.domain.tree.TreeItem;

import jakarta.persistence.TypedQuery;

@Repository("WebFeed")
@Transactional
public class WebFeedDAO extends AbstractGenericDAO<WebFeed> {

    @Transactional
    @Override
    public void delete(final long id) {
        final WebFeed inst = this.em.find(WebFeed.class, id);
        this.em.remove(inst);
    }

    @Transactional(readOnly = true)
    @Override
    public WebFeed findById(final long id) {
        return this.em.find(WebFeed.class, id);
    }

    @Transactional(readOnly = true)
    public TreeItem findAsTreeItem(final long id) {
        final WebFeed feed = this.findById(id);
        return new TreeItem(feed);
    }

    @Transactional(readOnly = true)
    public List<TreeItem> findChildTreeItems(final long id) {
        final List<WebFeed> feeds = this.findByParentId(id);
        final ArrayList<TreeItem> results = new ArrayList<>();
        for (final WebFeed feed : feeds) {
            final TreeItem item = new TreeItem(feed);
            results.add(item);
        }
        return results;
    }

    @Transactional(readOnly = true)
    @Override
    public List<WebFeed> findAll() {
        final TypedQuery<WebFeed> query = this.em.createQuery("SELECT wf FROM WebFeed wf", WebFeed.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<WebFeed> getFeedsUnderRoot() {
        final TypedQuery<WebFeed> query = this.em.createQuery("SELECT wf FROM WebFeed wf WHERE wf.parent = NULL", WebFeed.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<WebFeed> findByParentId(final Long id) {
        final TypedQuery<WebFeed> query = this.em.createQuery("SELECT wf FROM WebFeed wf WHERE wf.parent.id = :id", WebFeed.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Transactional
    public boolean alreadyExists(final WebFeed feed) {
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
