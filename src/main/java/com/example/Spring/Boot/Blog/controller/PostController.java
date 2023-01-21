package com.example.Spring.Boot.Blog.controller;

import com.example.Spring.Boot.Blog.dto.CategoryDto;
import com.example.Spring.Boot.Blog.dto.PostDto;
import com.example.Spring.Boot.Blog.dto.PostDtoV2;
import com.example.Spring.Boot.Blog.dto.PostResponse;
import com.example.Spring.Boot.Blog.service.CategoryService;
import com.example.Spring.Boot.Blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.Spring.Boot.Blog.utils.AppConstants.*;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    //no need to autowire  for class created with bean and has one constr
    public PostController(PostService postService, CategoryService categoryService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/v1/posts")
    ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping("/v1/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                                    @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = DEFAULT_PAGE_SORT_COLUMN, required = false) String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = DEFAULT_PAGE_SORT_DIR, required = false) String sortDir) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPost(pageNo, pageSize, sortBy, sortDir));
    }


    @GetMapping(value = "/v1/posts/{id}",params = "version=1",
            headers="X-API-VERSION=1",produces = "application/vnd.phoenix.v1+json")
    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable(name = "id") long id) {
        PostDto postDto = postService.getPostById(id);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping(value = "/v2/posts/{id}",params = "version=2",
            headers="X-API-VERSION=2",produces = "application/vnd.phoenix.v2+json")
    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable(name = "id") long id) {
        PostDto postDto = postService.getPostById(id);
        PostDtoV2 pstV2 = new PostDtoV2();
        pstV2.setId(postDto.getId());
        pstV2.setContent(postDto.getContent());
        pstV2.setDescription(postDto.getDescription());
        pstV2.setTitle(postDto.getTitle());
        List<String> tags = new ArrayList<>();
        tags.add("Spring Boot");
        tags.add("AWS");
        tags.add("Docker");
        pstV2.setTags(tags);
        return new ResponseEntity<>(pstV2, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
        PostDto updatedPost = postService.updatePostById(postDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPost);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/v1/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePostById(id);
        String response = String.format("Post with id =%s Deleted Successfully", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/v1/posts/{postId}/categories")
    public ResponseEntity<CategoryDto> getCategoryByPost(@PathVariable(name = "postId") long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getCategoryByPost(postId));
    }

    @GetMapping("/v1/posts/categories/{id}/allposts")
    public ResponseEntity<List<PostDto>> getAllPostsByCategoryId(@PathVariable(name = "id") long categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPostsByCategoryId(categoryId));
    }
}
