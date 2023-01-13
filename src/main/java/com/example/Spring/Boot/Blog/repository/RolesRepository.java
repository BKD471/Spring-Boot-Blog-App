package com.example.Spring.Boot.Blog.repository;

import com.example.Spring.Boot.Blog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
