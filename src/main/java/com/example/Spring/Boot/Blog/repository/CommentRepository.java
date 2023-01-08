package com.example.Spring.Boot.Blog.repository;

import com.example.Spring.Boot.Blog.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments,Long>{
}
