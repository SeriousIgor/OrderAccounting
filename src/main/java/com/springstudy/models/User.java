package com.springstudy.models;

import com.springstudy.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Table(name = "custom_user")
@Entity(name = "User")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "service_sequence",
            sequenceName = "service_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "service_sequence"
    )
    @Column(
            name = "user_id",
            updatable = false
    )
    private Integer id;
    @Column(
            name = "username",
            unique = true,
            nullable = false
    )
    private String username;
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @Column(
            name = "email",
            unique = true,
            nullable = false
    )
    private String email;
    @Column(
            name = "first_name"
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;
    @Column(
            name = "password",
            nullable = false
    )
    private String password;
    @Column(
            name = "is_admin",
            columnDefinition="Boolean default 'false'",
            nullable = false
    )
    private Boolean isAdmin;
    @Column(
            name = "is_deleted",
            columnDefinition="Boolean default 'false'",
            nullable = false
    )
    private Boolean isDeleted;

    @OneToMany(mappedBy = "user")
    private Set<WorkLog> workLogs = new HashSet<>();

    public User() {
    }

    public User(String email, String username, String firstName, String lastName, String password) {
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.id = null;
        this.isAdmin = false;
        this.isDeleted = false;
    }

    public User(Integer id, String username, String email, String firstName, String lastName, String password, Boolean isAdmin, Boolean isDeleted) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.getRole().toString()));
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !this.isDeleted;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Role getRole() {
        return this.isAdmin ? Role.ADMIN : Role.USER;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", isDeleted=" + isDeleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return (
                (this == o) ||
                        ((o != null) && (getClass() == o.getClass())) ||
                        Objects.equals(getId(),((User) o).getId())
        );
    }
}
