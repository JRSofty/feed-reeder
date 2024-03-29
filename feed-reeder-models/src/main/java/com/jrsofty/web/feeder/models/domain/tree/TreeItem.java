package com.jrsofty.web.feeder.models.domain.tree;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

import com.jrsofty.web.feeder.models.domain.FeedItem;
import com.jrsofty.web.feeder.models.domain.GroupFeed;
import com.jrsofty.web.feeder.models.domain.WebFeed;

public class TreeItem implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7496498502944283190L;
    private long id;
    private Long parentId = null;
    private String title;
    private HashSet<TreeItem> children = new HashSet<>();
    private TreeItemType itemType = TreeItemType.WEB_FEED;
    private final HashMap<String, String> properties = new HashMap<>();

    public TreeItem() {

    }

    public TreeItem(GroupFeed group) {
        this.itemType = TreeItemType.GROUP_FEED;
        this.id = group.getId();
        if (group.getParent() != null) {
            this.parentId = group.getParent().getId();
        }
        this.title = group.getTitle();
    }

    public TreeItem(WebFeed webFeed) {
        this.id = webFeed.getId();
        if (webFeed.getParent() != null) {
            this.parentId = webFeed.getParent().getId();
        }
        this.title = webFeed.getTitle();
    }

    public TreeItem(FeedItem feedItem) {
        this.id = feedItem.getId();
        this.title = feedItem.getTitle();
        this.parentId = feedItem.getParent().getId();
        this.itemType = TreeItemType.FEED_ITEM;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashSet<TreeItem> getChildren() {
        return this.children;
    }

    public void setChildren(HashSet<TreeItem> children) {
        this.children = children;
    }

    public TreeItemType getItemType() {
        return this.itemType;
    }

    public void setItemType(TreeItemType itemType) {
        this.itemType = itemType;
    }

    public void setProperty(String property, String value) {
        this.properties.put(property, value);
    }

    public boolean hasProperty(String property) {
        return this.properties.containsKey(property);
    }

    public String getProperty(String property) {
        return this.properties.get(property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.itemType, this.parentId, this.title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((null == obj) || (this.getClass() != obj.getClass())) {
            return false;
        }
        final TreeItem other = (TreeItem) obj;
        return (this.id == other.id) && (this.itemType == other.itemType) && Objects.equals(this.parentId, other.parentId) && Objects.equals(this.title, other.title);
    }

}
