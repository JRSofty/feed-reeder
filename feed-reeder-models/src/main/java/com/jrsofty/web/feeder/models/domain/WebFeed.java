package com.jrsofty.web.feeder.models.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "web_feed")
public class WebFeed extends Feed {
    /**
     *
     */
    private static final long serialVersionUID = 2388357163580158879L;
    private String htmlUrl;
    private String feedUrl;
    private FeedType feedType;
    private Date lastUpdateAttempt;
    private Date lastUpdateSuccess;
    private Date lastUpdateFailure;
    private String lastFailureReason;
    private String cronExpression = "0 15 10 * * ?";
    @Column(name = "parent_id")
    private GroupFeed parent = null;

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

    public GroupFeed getParent() {
        return this.parent;
    }

    public void setParent(GroupFeed parent) {
        this.parent = parent;
    }

}
