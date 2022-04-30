package com.jrsofty.web.feeder.models.domain;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "group_feed")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class GroupFeed extends Feed {

    /**
     *
     */
    private static final long serialVersionUID = 8806542464451784728L;

    @OneToMany(mappedBy = "parent_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private final ArrayList<Feed> children = new ArrayList<>();

    public void addChild(Feed feed) {
        feed.setParent(this);
        this.children.add(feed);
    }

    public void removeChild(Feed feed) {
        this.children.remove(feed);
        feed.setParent(null);
    }

    public ArrayList<Feed> getChildren() {
        return this.children;
    }

}
