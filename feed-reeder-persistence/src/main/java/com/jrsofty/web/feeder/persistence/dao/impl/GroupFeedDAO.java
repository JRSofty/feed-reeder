package com.jrsofty.web.feeder.persistence.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jrsofty.web.feeder.models.domain.GroupFeed;

@Repository("GroupFeed")
public class GroupFeedDAO extends AbstractGenericDAO<GroupFeed> {

    @Override
    public void delete(long id) {
        final GroupFeed inst = this.em.find(GroupFeed.class, id);
        this.em.remove(inst);
    }

    @Override
    public GroupFeed findById(long id) {
        return this.em.find(GroupFeed.class, id);
    }

    @Override
    public List<GroupFeed> findAll() {
        final TypedQuery<GroupFeed> query = this.em.createQuery("SELECT gf FROM GroupFeed gf", GroupFeed.class);
        return query.getResultList();
    }

}
