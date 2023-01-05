package com.example.Spring.Boot.Blog.service;

import com.example.Spring.Boot.Blog.dto.PostDto;

import java.util.List;

public interface PostService {
     PostDto createPost(PostDto postDto);
     List<PostDto> getAllPost();

     PostDto getPostById(long id);
     PostDto updatePostById(PostDto postDto,long id);
     boolean deletePostById(long id);
}
