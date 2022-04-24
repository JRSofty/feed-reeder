package com.jrsofty.web.feeder.models.domain;

import java.io.Serializable;

public abstract class Feed implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4427014166275768310L;

    private Long id;
    private String title;
    private String description;
    private Feed parent = null;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Feed getParent() {
        return this.parent;
    }

    public void setParent(Feed parent) {
        this.parent = parent;
    }

}
