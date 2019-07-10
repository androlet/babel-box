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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.learning.babelbox.features.UserController.LOGIN_URL;
import static java.util.Arrays.asList;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String AUTHENTICATION_COOKIE_NAME = "Bearer";

    @Autowired
    private UserService userService;

    @Value("${front-end.url}")
    public static String frontEndUrl;

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthTokenProvider authTokenProvider() {
        return new AuthTokenProvider(userService);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(asList(frontEndUrl));
        configuration.setAllowedMethods(asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .authorizeRequests().antMatchers(LOGIN_URL).anonymous()
                .and()
                .addFilterAt(authTokenFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests().anyRequest().authenticated();
    }

}
