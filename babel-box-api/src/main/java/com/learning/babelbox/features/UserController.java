package com.learning.babelbox.features;

import com.learning.babelbox.domain.User;
import com.learning.babelbox.features.dto.Credentials;
import com.learning.babelbox.platform.UUIDProvider;
import com.learning.babelbox.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.learning.babelbox.security.SecurityConfig.AUTHENTICATION_COOKIE_NAME;

@RestController
public class UserController {

    public static final String LOGIN_URL = "/api/login";

    private final boolean skipHTTPs;
    private final UserService userService;
    private final UUIDProvider uuidProvider;

    public UserController(
            @Value("${env.https.skip:false}") boolean skipHTTPs,
            UserService userService,
            UUIDProvider uuidProvider) {
        this.skipHTTPs = skipHTTPs;
        this.userService = userService;
        this.uuidProvider = uuidProvider;
    }

    private Cookie generateAuthenticationCookie(String token) {
        Cookie cookie = new Cookie(AUTHENTICATION_COOKIE_NAME, token);
        cookie.setHttpOnly(true);
        cookie.setSecure(!skipHTTPs);
        return cookie;
    }

    @PostMapping(value = LOGIN_URL)
    public ResponseEntity login(@RequestBody Credentials credentials, HttpServletResponse response) {
        User connectingUser = userService.loadUserByUsername(credentials.username);
        if (null == connectingUser || !connectingUser.getPassword().equals(credentials.password)) {
            return ResponseEntity.badRequest().build();
        }
        String token = uuidProvider.getRandomUUID();
        connectingUser.setToken(token);
        userService.save(connectingUser);
        response.addCookie(generateAuthenticationCookie(token));
        return ResponseEntity.noContent().build();
    }
}
