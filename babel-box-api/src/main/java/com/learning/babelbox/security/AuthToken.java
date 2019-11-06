package com.learning.babelbox.security;

import com.learning.babelbox.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;
import java.util.Collections;

public class AuthToken implements Authentication {

    private final String token;
    private final User user;

    public AuthToken(String token, User user) {
        this.token = token;
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return user != null;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
