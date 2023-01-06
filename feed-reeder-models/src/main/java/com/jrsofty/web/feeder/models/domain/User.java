package com.jrsofty.web.feeder.models.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -8013833765801793070L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private final Set<Role> roles = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void addRole(final Role role) {
        if (!this.roles.contains(role)) {
            role.addUser(this);
            this.roles.add(role);
        }
    }

    public void removeRole(final Role role) {
        if (this.roles.contains(role)) {
            role.removeUser(this);
            this.roles.remove(role);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.email, this.id, this.password, this.username);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.email, other.email) && Objects.equals(this.id, other.id)
                && Objects.equals(this.password, other.password) && Objects.equals(this.username, other.username);
    }

    @Override
    public String toString() {
        return "User [id=" + this.id + ", username=" + this.username + ", password=" + this.password + ", email=" + this.email + ", roles="
                + this.roles + "]";
    }
}
