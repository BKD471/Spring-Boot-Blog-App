package com.example.Spring.Boot.Blog.repository;

import com.example.Spring.Boot.Blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsernameOrEmail(String username, String email);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
