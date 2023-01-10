package com.example.Spring.Boot.Blog.controller;

import com.example.Spring.Boot.Blog.dto.CommentDto;
import com.example.Spring.Boot.Blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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

    @GetMapping("/posts/{postId}/comments")
    List<CommentDto> getAllComments(@PathVariable(value="postId") long postId){
       return commentService.getAllCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    ResponseEntity<CommentDto> getCommentByCommentId(@PathVariable(value="postId") long postId,@PathVariable(value="commentId") long  commentId){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentByCommentId(postId,commentId));
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    ResponseEntity<CommentDto> updateCommentByCommentId(@PathVariable(value="postId") long postId
            ,@PathVariable(value="commentId") long commentId,@RequestBody  CommentDto commentDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.updateComment(postId, commentId, commentDto));
    }

    @DeleteMapping("posts/{postId}/comments/{commentId}")
    ResponseEntity<String> deleteComment(@PathVariable(value="postId") long postId,
                                         @PathVariable(value="commentId") long commentId){
        commentService.deleteCommentById(postId,commentId);
        String responseMessage=String.format("Comment with Id %s deleted successfully",commentId);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }
}
