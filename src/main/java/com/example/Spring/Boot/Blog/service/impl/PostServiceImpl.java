package com.example.Spring.Boot.Blog.service.impl;

import com.example.Spring.Boot.Blog.dto.PostDto;
import com.example.Spring.Boot.Blog.dto.PostResponse;
import com.example.Spring.Boot.Blog.model.Post;
import com.example.Spring.Boot.Blog.repository.PostRepository;
import com.example.Spring.Boot.Blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

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
        PostDto postResponse = mapToDto(postCreated);
        return postResponse;
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir) {
        //Getting the sort direction
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        //create Pageable Instances
        Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
        Page<Post> posts = postRepository.findAll(pageable);

        //Get all pages
        List<Post> allPosts = posts.getContent();
        List<PostDto> content= allPosts.stream().map(t -> mapToDto(t)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            return mapToDto(post.get());
        }
        return null;
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            Post fetchedPost = post.get();
            Post updatedPost = mapToEntity(postDto);
            fetchedPost.setTitle(updatedPost.getTitle());
            fetchedPost.setDescription(updatedPost.getDescription());
            fetchedPost.setContent(updatedPost.getContent());
            Post newlyUpdatedAndSavedPost = postRepository.save(fetchedPost);
            return mapToDto(newlyUpdatedAndSavedPost);
        }
        return null;
    }

    @Override
    public boolean deletePostById(long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        return postDto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }
}
