package com.jrsofty.web.feeder.models.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
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
    @JoinColumn(name = "parent_id", nullable = false, insertable = true, updatable = false)
    private WebFeed parent;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = true, updatable = false)
    private User user;
    @Column(name = "link_url", nullable = false, length = 255)
    private String linkUrl;
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    @Column(name = "pub_date", nullable = false)
    private String pubDate;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "viewed", nullable = false)
    private boolean viewed = false;
    @Column(name = "received")
    private Date received;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public WebFeed getParent() {
        return this.parent;
    }

    public void setParent(final WebFeed parent) {
        this.parent = parent;
    }

    public String getLinkUrl() {
        return this.linkUrl;
    }

    public void setLinkUrl(final String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getPubDate() {
        return this.pubDate;
    }

    public void setPubDate(final String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public boolean isViewed() {
        return this.viewed;
    }

    public void setViewed(final boolean viewed) {
        this.viewed = viewed;
    }

    public void setReceived(final Date received) {
        this.received = received;
    }

    public Date getReceived() {
        return this.received;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.description, this.linkUrl, this.pubDate, this.title);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }
        final FeedItem other = (FeedItem) obj;

        final boolean result = Objects.equals(this.description, other.description) && Objects.equals(this.linkUrl, other.linkUrl)
                && Objects.equals(this.pubDate, other.pubDate)
                && Objects.equals(this.title, other.title);
        return result;
    }

}
