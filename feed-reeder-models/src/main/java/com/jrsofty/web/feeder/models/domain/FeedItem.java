package com.jrsofty.web.feeder.models.domain;

import java.io.Serializable;
import java.util.Date;

public class FeedItem implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1793569421386855377L;
    private Long id;
    private WebFeed parent;
    private String linkUrl;
    private String title;
    private Date pubDate;
    private String description;
    private boolean viewed = false;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WebFeed getParent() {
        return this.parent;
    }

    public void setParent(WebFeed parent) {
        this.parent = parent;
    }

    public String getLinkUrl() {
        return this.linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPubDate() {
        return this.pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isViewed() {
        return this.viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

}
