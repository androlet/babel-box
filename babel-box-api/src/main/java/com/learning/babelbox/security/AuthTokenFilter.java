package com.learning.babelbox.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static com.learning.babelbox.security.SecurityConfig.AUTHENTICATION_COOKIE_NAME;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private AuthTokenProvider authTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<Cookie> cookieOpt = request.getCookies() == null ? Optional.empty() : Arrays.stream(request.getCookies())
                .filter(cookie -> AUTHENTICATION_COOKIE_NAME.equals(cookie.getName())).findFirst();
        if(cookieOpt.isPresent()) {
            Cookie cookie = cookieOpt.get();
            authTokenProvider.authenticate(cookie.getValue());
        }
        filterChain.doFilter(request, response);
    }
}
