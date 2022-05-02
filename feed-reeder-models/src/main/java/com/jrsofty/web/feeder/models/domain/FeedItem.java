package com.jrsofty.web.feeder.models.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "feed_item")
public class FeedItem implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1793569421386855377L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "parent_id", insertable = true, updatable = false)
    private WebFeed parent;
    @Column(name = "link_url", nullable = false, length = 255)
    private String linkUrl;
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    @Column(name = "pub_date", nullable = false)
    private Date pubDate;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "viewed", nullable = false)
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

    @Override
    public int hashCode() {
        return Objects.hash(this.description, this.linkUrl, this.parent, this.pubDate, this.title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }
        final FeedItem other = (FeedItem) obj;
        return Objects.equals(this.description, other.description) && Objects.equals(this.linkUrl, other.linkUrl) && Objects.equals(this.parent, other.parent)
                && Objects.equals(this.pubDate, other.pubDate)
                && Objects.equals(this.title, other.title);
    }

}
