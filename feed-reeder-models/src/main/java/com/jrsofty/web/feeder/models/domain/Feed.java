package com.jrsofty.web.feeder.models.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Feed implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4427014166275768310L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    @Column(name = "description", nullable = true, length = 255)
    private String description;
    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true, insertable = true, updatable = false)
    private GroupFeed parent;

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

    public void setParent(GroupFeed parent) {
        this.parent = parent;
    }

    public GroupFeed getParent() {
        return this.parent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }
        final Feed other = (Feed) obj;
        return Objects.equals(this.title, other.title);
    }

}
