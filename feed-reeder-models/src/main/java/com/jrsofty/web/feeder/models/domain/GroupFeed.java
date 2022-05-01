package com.jrsofty.web.feeder.models.domain;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<GroupFeed> childGroups = new ArrayList<>();
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<WebFeed> childFeeds = new ArrayList<>();

    public void addChildGroup(GroupFeed feed) {
        this.childGroups.add(feed);
        feed.setParent(this);
    }

    public void removeChildGroup(GroupFeed feed) {
        this.childGroups.remove(feed);
        feed.setParent(null);
    }

    public void addChildFeed(WebFeed feed) {
        this.childFeeds.add(feed);
        feed.setParent(this);
    }

    public void removeChildFeed(WebFeed feed) {
        this.childFeeds.remove(feed);
        feed.setParent(null);
    }

    public List<WebFeed> getChildFeeds() {
        return this.childFeeds;
    }

    public List<GroupFeed> getChildGroups() {
        return this.childGroups;
    }

    @Override
    public int hashCode() {

        final int result = super.hashCode();
        // result = (prime * result) + Objects.hash(this.childGroups);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || (this.getClass() != obj.getClass())) {
            return false;
        }
        return true;
    }

}
