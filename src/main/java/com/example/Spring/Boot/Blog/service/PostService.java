package com.example.Spring.Boot.Blog.service;

import com.example.Spring.Boot.Blog.dto.PostDto;
import com.example.Spring.Boot.Blog.dto.PostResponse;


public interface PostService {
     PostDto createPost(PostDto postDto);
     PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir);

     PostDto getPostById(long id);
     PostDto updatePostById(PostDto postDto,long id);
     void deletePostById(long id);
}
