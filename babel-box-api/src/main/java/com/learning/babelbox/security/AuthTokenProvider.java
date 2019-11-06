package com.learning.babelbox.security;

import com.learning.babelbox.domain.User;
import com.learning.babelbox.services.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuthTokenProvider implements AuthenticationProvider {

    private final UserService userService;

    public AuthTokenProvider(UserService userService) {
        this.userService = userService;
    }

    public void authenticate(String token) throws AuthenticationException {
        Optional<User> userOpt = userService.findEnabledUserByToken(token);
        userOpt.ifPresent(user -> authenticate(new AuthToken(token, user)));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
