package com.example.Spring.Boot.Blog.repository;

import com.example.Spring.Boot.Blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
