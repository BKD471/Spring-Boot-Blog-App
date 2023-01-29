package com.example.Spring.Boot.Blog.controller;

import com.example.Spring.Boot.Blog.dto.CommentDto;
import com.example.Spring.Boot.Blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;
    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/v1/posts/{postId}/comments")
    ResponseEntity<CommentDto> createComent(@PathVariable(value="postId") long postId,@Valid @RequestBody CommentDto commentDto){
        CommentDto fetchedCommentDto=commentService.createComment(commentDto,postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(fetchedCommentDto);
    }

    @GetMapping("/v1/posts/{postId}/comments")
    ResponseEntity<List<CommentDto>> getAllComments(@PathVariable(value="postId") long postId){
       return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsByPostId(postId));
    }

    @GetMapping("/v1/posts/{postId}/comments/{commentId}")
    ResponseEntity<CommentDto> getCommentByCommentId(@PathVariable(value="postId") long postId,@PathVariable(value="commentId") long  commentId){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentByCommentId(postId,commentId));
    }

    @PutMapping("/v1/posts/{postId}/comments/{commentId}")
    ResponseEntity<CommentDto> updateCommentByCommentId(@PathVariable(value="postId") long postId
            ,@PathVariable(value="commentId") long commentId,@Valid @RequestBody  CommentDto commentDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.updateComment(postId, commentId, commentDto));
    }

    @DeleteMapping("/v1/posts/{postId}/comments/{commentId}")
    ResponseEntity<String> deleteComment(@PathVariable(value="postId") long postId,
                                         @PathVariable(value="commentId") long commentId){
        commentService.deleteCommentById(postId,commentId);
        String responseMessage=String.format("Comment with Id %s deleted successfully",commentId);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }
}
