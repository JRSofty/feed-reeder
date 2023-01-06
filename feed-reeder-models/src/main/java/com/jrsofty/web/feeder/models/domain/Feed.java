package com.jrsofty.web.feeder.models.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

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
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = true, updatable = false)
    private User user;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setParent(final GroupFeed parent) {
        this.parent = parent;
    }

    public GroupFeed getParent() {
        return this.parent;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.parent, this.title, this.user);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }
        final Feed other = (Feed) obj;
        return Objects.equals(this.id, other.id) && Objects.equals(this.parent, other.parent) && Objects.equals(this.title, other.title) && Objects.equals(this.user, other.user);
    }

}
