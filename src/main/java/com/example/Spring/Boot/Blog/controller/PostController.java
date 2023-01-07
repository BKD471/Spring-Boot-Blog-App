package com.example.Spring.Boot.Blog.controller;

import com.example.Spring.Boot.Blog.dto.PostDto;
import com.example.Spring.Boot.Blog.dto.PostResponse;
import com.example.Spring.Boot.Blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;
import static com.example.Spring.Boot.Blog.utils.AppConstants.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    //no need to autowire  for class created with bean and has one constr
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
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
        if (Objects.isNull(postDto)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
        PostDto updatedPost = postService.updatePostById(postDto, id);
        if (Objects.isNull(updatedPost)) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        if (postService.deletePostById(id)) {
            String response = String.format("Post with id =%s Deleted Successfully", id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        String response = String.format("Given post id =%s is invalid", id);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
