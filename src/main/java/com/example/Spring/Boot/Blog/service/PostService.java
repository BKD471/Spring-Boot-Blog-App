package com.example.Spring.Boot.Blog.service;

import com.example.Spring.Boot.Blog.dto.PostDto;
import com.example.Spring.Boot.Blog.dto.PostResponse;

import java.util.List;

public interface PostService {
     PostDto createPost(PostDto postDto);
     PostResponse getAllPost(int pageNo, int pageSize);

     PostDto getPostById(long id);
     PostDto updatePostById(PostDto postDto,long id);
     boolean deletePostById(long id);
}
