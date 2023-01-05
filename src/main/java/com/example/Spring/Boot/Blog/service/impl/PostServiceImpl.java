package com.example.Spring.Boot.Blog.service.impl;

import com.example.Spring.Boot.Blog.dto.PostDto;
import com.example.Spring.Boot.Blog.model.Post;
import com.example.Spring.Boot.Blog.repository.PostRepository;
import com.example.Spring.Boot.Blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //convert DTO to entity;
        Post post = mapToEntity(postDto);

        Post postCreated = postRepository.save(post);

        //convert entity to DTO
        PostDto postResponse=mapToDto(postCreated);
        return postResponse;
    }

    @Override
    public List<PostDto> getAllPost(){
        List<Post> allPosts=postRepository.findAll();
        System.out.println(allPosts);
        return allPosts.stream().map(t-> mapToDto(t)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id){
        Optional<Post> post=postRepository.findById(id);
        if(post.isPresent()){
            return mapToDto(post.get());
        }
        return null;
    }

    @Override
    public PostDto updatePostById(PostDto postDto,long id){
       Optional<Post> post=postRepository.findById(id);
        if(post.isPresent()){
            Post fetchedPost=post.get();
            Post updatedPost=mapToEntity(postDto);
            fetchedPost.setTitle(updatedPost.getTitle());
            fetchedPost.setDescription(updatedPost.getDescription());
            fetchedPost.setContent(updatedPost.getContent());
            Post newlyUpdatedAndSavedPost=postRepository.save(fetchedPost);
            return mapToDto(newlyUpdatedAndSavedPost);
        }
        return null;
    }

    @Override
    public boolean deletePostById(long id){
        Optional<Post> post=postRepository.findById(id);
        if(post.isPresent()){
            postRepository.deleteById(id);
            return  true;
        }
        return false;
    }

    private PostDto mapToDto(Post post){
        PostDto postDto=new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        return postDto;
    }

    private Post mapToEntity(PostDto postDto){
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }
}
