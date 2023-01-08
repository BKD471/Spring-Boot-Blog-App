package com.example.Spring.Boot.Blog.controller;

import com.example.Spring.Boot.Blog.dto.CommentDto;
import com.example.Spring.Boot.Blog.model.Comments;
import com.example.Spring.Boot.Blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private final CommentService commentService;
    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    ResponseEntity<CommentDto> createComent(@PathVariable(value="postId") long postId, @RequestBody CommentDto commentDto){
        CommentDto fetchedCommentDto=commentService.createComment(commentDto,postId);
        if(Objects.isNull(fetchedCommentDto)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(fetchedCommentDto);
    }
}
