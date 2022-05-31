package com.jrsofty.web.feeder.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jrsofty.web.feeder.models.domain.WebFeed;
import com.jrsofty.web.feeder.models.domain.tree.TreeItem;

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
    public TreeItem findAsTreeItem(long id) {
        final WebFeed feed = this.findById(id);
        return new TreeItem(feed);
    }

    @Transactional(readOnly = true)
    public List<TreeItem> findChildTreeItems(long id) {
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
    public List<WebFeed> findByParentId(Long id) {
        final TypedQuery<WebFeed> query = this.em.createQuery("SELECT wf FROM WebFeed wf WHERE wf.parent.id = :id", WebFeed.class);
        return query.getResultList();
    }

    @Transactional
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
