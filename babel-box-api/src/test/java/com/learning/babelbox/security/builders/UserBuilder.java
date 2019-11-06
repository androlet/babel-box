package com.learning.babelbox.security.builders;

import com.learning.babelbox.domain.User;

public class UserBuilder {

    public static User buildActiveUserFrom(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setActive(true);
        return user;
    }

    public static User buildActiveUserFrom(String email, String password, String token) {
        User user = buildActiveUserFrom(email, password);
        user.setToken(token);
        return user;
    }

    public static User buildDisabledUserFrom(String email, String password) {
        User user = buildActiveUserFrom(email, password);
        user.setActive(false);
        return user;
    }

    public static User buildDisabledUserFrom(String email, String password, String token) {
        User user = buildActiveUserFrom(email, password, token);
        user.setActive(false);
        return user;
    }
}
