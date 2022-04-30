package com.jrsofty.web.feeder.models.domain;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "web_feed")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class WebFeed extends Feed {
    /**
     *
     */
    private static final long serialVersionUID = 2388357163580158879L;
    @Column(name = "html_url", nullable = true, length = 255)
    private String htmlUrl;
    @Column(name = "feed_url", nullable = false, length = 255)
    private String feedUrl;
    @Column(name = "feed_type", nullable = false)
    private FeedType feedType;
    @Column(name = "last_update_attempt", nullable = true)
    private Date lastUpdateAttempt;
    @Column(name = "last_update_success", nullable = true)
    private Date lastUpdateSuccess;
    @Column(name = "last_update_failure", nullable = true)
    private Date lastUpdateFailure;
    @Column(name = "last_failure_reason", nullable = true, length = 255)
    private String lastFailureReason;
    @Column(name = "cron_expression", nullable = false, length = 40)
    private String cronExpression = "0 15 10 * * ?";
    @OneToMany(mappedBy = "parent_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private final ArrayList<FeedItem> feeditems = new ArrayList<>();

    public String getHtmlUrl() {
        return this.htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getFeedUrl() {
        return this.feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    public FeedType getFeedType() {
        return this.feedType;
    }

    public void setFeedType(FeedType feedType) {
        this.feedType = feedType;
    }

    public Date getLastUpdateAttempt() {
        return this.lastUpdateAttempt;
    }

    public void setLastUpdateAttempt(Date lastUpdateAttempt) {
        this.lastUpdateAttempt = lastUpdateAttempt;
    }

    public Date getLastUpdateSuccess() {
        return this.lastUpdateSuccess;
    }

    public void setLastUpdateSuccess(Date lastUpdateSuccess) {
        this.lastUpdateSuccess = lastUpdateSuccess;
    }

    public Date getLastUpdateFailure() {
        return this.lastUpdateFailure;
    }

    public void setLastUpdateFailure(Date lastUpdateFailure) {
        this.lastUpdateFailure = lastUpdateFailure;
    }

    public String getLastFailureReason() {
        return this.lastFailureReason;
    }

    public void setLastFailureReason(String lastFailureReason) {
        this.lastFailureReason = lastFailureReason;
    }

    public String getCronExpression() {
        return this.cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public void addFeeditem(FeedItem item) {
        item.setParent(this);
        this.feeditems.add(item);
    }

    public void removeFeeditem(FeedItem item) {
        this.feeditems.remove(item);
        item.setParent(null);
    }

    public ArrayList<FeedItem> getFeeditems() {
        return this.feeditems;
    }

}
