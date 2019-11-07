package com.learning.babelbox.services;

import com.learning.babelbox.domain.User;
import com.learning.babelbox.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User current() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    @Transactional(readOnly = true)
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    @Transactional(readOnly = true)
    public User loadEnabledUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findEnabledUserByEmail(username).orElse(null);
    }

    @Transactional(readOnly = true)
    public Optional<User> findEnabledUserByToken(String token) {
        return userRepository.findEnabledUserByToken(token);
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}
