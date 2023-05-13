package com.springstudy.security.services;

import com.springstudy.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDetailsImplementation implements UserDetails {

    private final User user;

    private final GrantedAuthority authority;

    public UserDetailsImplementation(User user, GrantedAuthority authority) {
        this.user = user;
        this.authority = authority;
    }

    public static UserDetailsImplementation build(User user) {
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString());

        return new UserDetailsImplementation(user, authority);
    }

    public Integer getId() {
        return this.user.getUserId();
    }

    public String getEmail() {
        return this.user.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(this.authority);
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
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
        return !(this.user.getIsDeleted());
    }

    @Override
    public boolean equals(Object o) {
        return (
                (this == o) ||
                ((o != null) && (getClass() == o.getClass())) ||
                Objects.equals(getId(),((UserDetailsImplementation) o).getId())
        );
    }
}
