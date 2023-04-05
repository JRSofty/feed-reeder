package com.jrsofty.web.feeder.models.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "right")
public class Right {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToMany(mappedBy = "rights")
    private final Set<Role> roles = new HashSet<>();

    public void addRole(final Role role) {
        this.roles.add(role);
    }

    public void removeRole(final Role role) {
        this.roles.remove(role);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }
        final Right other = (Right) obj;
        return Objects.equals(this.id, other.id) && Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return "Right [id=" + this.id + ", name=" + this.name + ", roles=" + this.roles + "]";
    }

}
