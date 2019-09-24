package com.learning.babelbox.security;

import com.learning.babelbox.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static com.learning.babelbox.features.UserController.LOGIN_URL;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String AUTHENTICATION_COOKIE_NAME = "Bearer";

    @Autowired
    private UserService userService;

    @Value("${cors.allowed-origin.urls}")
    private String allowedUrls;

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthTokenProvider authTokenProvider() {
        return new AuthTokenProvider(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().disable()
                .authorizeRequests().antMatchers("/web/**", LOGIN_URL).permitAll()
                .and()
                .addFilterAt(authTokenFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests().antMatchers("/api/**").authenticated();
    }

}
