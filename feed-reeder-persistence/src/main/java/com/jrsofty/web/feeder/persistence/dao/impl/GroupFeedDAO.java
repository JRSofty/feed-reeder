package com.jrsofty.web.feeder.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jrsofty.web.feeder.models.domain.GroupFeed;
import com.jrsofty.web.feeder.models.domain.tree.TreeItem;

import jakarta.persistence.TypedQuery;

@Repository("GroupFeed")
@Transactional
public class GroupFeedDAO extends AbstractGenericDAO<GroupFeed> {
    @Transactional
    @Override
    public void delete(final long id) {
        final GroupFeed inst = this.em.find(GroupFeed.class, id);
        this.em.remove(inst);
    }

    @Transactional(readOnly = true)
    @Override
    public GroupFeed findById(final long id) {
        return this.em.find(GroupFeed.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<GroupFeed> findAll() {
        final TypedQuery<GroupFeed> query = this.em.createQuery("SELECT gf FROM GroupFeed gf", GroupFeed.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public TreeItem findAsTreeItem(final long id) {
        final GroupFeed feed = this.findById(id);
        return new TreeItem(feed);
    }

    @Transactional(readOnly = true)
    public List<TreeItem> findChildTreeItems(final long id) {
        final List<GroupFeed> feeds = this.findByParentId(id);
        final ArrayList<TreeItem> results = new ArrayList<>();
        for (final GroupFeed feed : feeds) {
            final TreeItem item = new TreeItem(feed);
            results.add(item);
        }
        return results;
    }

    @Transactional(readOnly = true)
    public List<GroupFeed> findByParentId(final long id) {
        final TypedQuery<GroupFeed> query = this.em.createQuery("SELECT gf FROM GroupFeed gf WHERE gf.parent.id = :id", GroupFeed.class);
        query.setParameter("id", id);
        return query.getResultList();

    }

    @Transactional(readOnly = true)
    public GroupFeed findByNameAndParent(final GroupFeed parent, final String name) {
        TypedQuery<GroupFeed> query;
        if (parent == null) {
            query = this.em.createQuery("SELECT gf FROM GroupFeed gf WHERE gf.title = :name AND gf.parent IS NULL", GroupFeed.class);
            query.setParameter("name", name);
        } else {
            query = this.em.createQuery("SELECT gf FROM GroupFeed gf WHERE gf.title = :name AND gf.parent = :parent", GroupFeed.class);
            query.setParameter("name", name);
            query.setParameter("parent", parent);
        }

        return query.getSingleResult();
    }

    @Transactional(readOnly = true)
    public boolean alreadyExists(final GroupFeed feed) {
        final TypedQuery<Long> query = this.em.createQuery("SELECT COUNT(gf) FROM GroupFeed gf WHERE gf.title = :title AND gf.parent = :parent", Long.class);
        query.setParameter("title", feed.getTitle());
        query.setParameter("parent", feed.getParent());
        final Long cnt = query.getSingleResult();
        boolean result = false;
        if (cnt != 0) {
            result = true;
        }
        return result;
    }

}
