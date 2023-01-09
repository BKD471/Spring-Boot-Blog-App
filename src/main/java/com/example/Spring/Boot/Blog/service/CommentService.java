package com.example.Spring.Boot.Blog.service;

import com.example.Spring.Boot.Blog.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,long postId);
    List<CommentDto> getAllCommentsByPostId(long postId);
    CommentDto getCommentByCommentId(long postId,long commentId);
    CommentDto updateComment(long postId,long commentId,CommentDto commentDto);
    void deleteCommentById(long postId,long commentId);
}
