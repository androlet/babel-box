package com.learning.babelbox.mocks;

import com.learning.babelbox.domain.User;
import com.learning.babelbox.mocks.BaseRepositoryMock;
import com.learning.babelbox.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
public class UserRepositoryMock extends BaseRepositoryMock<User> implements UserRepository {

    public UserRepositoryMock() {
        super(User.class);
    }

    @Override
    public User findByEmail(String email) {
        return getRepositoryData().values().stream().filter(u -> u.getUsername().equals(email)).findFirst().orElse(null);
    }

    @Override
    public Optional<User> findEnabledUserByEmail(String email) {
        return Optional.ofNullable(
                getRepositoryData().values().stream().filter(
                        u -> u.getUsername().equals(email) && u.isEnabled()
                ).findFirst().orElse(null)
        );
    }

    @Override
    public Optional<User> findEnabledUserByToken(String token) {
        return getRepositoryData().values().stream().filter(u -> u.getToken().equals(token) && u.isEnabled()).findFirst();
    }
}
