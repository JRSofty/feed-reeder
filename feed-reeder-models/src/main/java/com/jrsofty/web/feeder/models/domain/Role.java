package com.jrsofty.web.feeder.models.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private final Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "role_right", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "right_id"))
    private final Set<Right> rights = new HashSet<>();

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

    public Set<User> getUsers() {
        return this.users;
    }

    public void addUser(final User user) {
        this.users.add(user);
    }

    public void removeUser(final User user) {
        this.users.remove(user);
    }

    public Set<Right> getRights() {
        return this.rights;
    }

    public void addRight(final Right right) {
        right.addRole(this);
        this.rights.add(right);
    }

    public void removeRight(final Right right) {
        right.removeRole(this);
        this.rights.remove(right);
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
        final Role other = (Role) obj;
        return Objects.equals(this.id, other.id) && Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return "Role [id=" + this.id + ", name=" + this.name + ", users=" + this.users + ", rights=" + this.rights + "]";
    }
}
