package com.learning.babelbox.security;

import com.learning.babelbox.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
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
    public AuthTokenProvider authTokenProvider() {
        return new AuthTokenProvider(userService);
    }

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public ExceptionHandlerFilter exceptionHandlerFilter() {
        return new ExceptionHandlerFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(exceptionHandlerFilter(), ChannelProcessingFilter.class)
                .csrf().ignoringAntMatchers(LOGIN_URL).disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().disable()
                .authorizeRequests().antMatchers(LOGIN_URL).permitAll()
                .and()
                .addFilterAt(authTokenFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests().antMatchers("/api/**").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
    }

}
