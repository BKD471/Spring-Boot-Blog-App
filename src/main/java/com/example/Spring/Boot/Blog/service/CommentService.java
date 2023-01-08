package com.example.Spring.Boot.Blog.service;

import com.example.Spring.Boot.Blog.dto.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,long id);
}
