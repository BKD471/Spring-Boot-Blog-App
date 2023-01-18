package com.example.Spring.Boot.Blog.controller;

import com.example.Spring.Boot.Blog.dto.CategoryDto;
import com.example.Spring.Boot.Blog.dto.PostDto;
import com.example.Spring.Boot.Blog.dto.PostResponse;
import com.example.Spring.Boot.Blog.service.CategoryService;
import com.example.Spring.Boot.Blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import static com.example.Spring.Boot.Blog.utils.AppConstants.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    //no need to autowire  for class created with bean and has one constr
    public PostController(PostService postService,CategoryService categoryService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPosts(@RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                    @RequestParam(value = "sortBy", defaultValue = DEFAULT_PAGE_SORT_COLUMN, required = false) String sortBy,
                                    @RequestParam(value = "sortDir", defaultValue = DEFAULT_PAGE_SORT_DIR, required = false) String sortDir) {
        return postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        PostDto postDto = postService.getPostById(id);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
        PostDto updatedPost = postService.updatePostById(postDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPost);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePostById(id);
        String response = String.format("Post with id =%s Deleted Successfully", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{postId}/categories")
    public CategoryDto getCategoryByPost(@PathVariable(name="postId") long postId){
        return postService.getCategoryByPost(postId);
    }
}
