package com.jrsofty.web.feeder.models.domain;

import javax.persistence.Column;

public class GroupFeed extends Feed {

    /**
     *
     */
    private static final long serialVersionUID = 8806542464451784728L;

    @Column(name = "parent_id")
    private GroupFeed parent = null;

    public GroupFeed getParent() {
        return this.parent;
    }

    public void setParent(GroupFeed parent) {
        this.parent = parent;
    }
}
