package com.learning.babelbox.repository;

import com.learning.babelbox.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("select u from User u where u.isActive = true and u.token = :token")
    Optional<User> findEnabledUserByToken(@Param("token") String token);
}
