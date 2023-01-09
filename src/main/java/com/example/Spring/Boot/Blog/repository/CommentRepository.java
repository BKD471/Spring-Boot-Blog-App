package com.example.Spring.Boot.Blog.repository;

import com.example.Spring.Boot.Blog.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments,Long>{
    List<Comments> findByPostId(long postId);
}
