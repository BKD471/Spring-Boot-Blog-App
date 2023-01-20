package com.example.Spring.Boot.Blog.service.impl;

import com.example.Spring.Boot.Blog.dto.CategoryDto;
import com.example.Spring.Boot.Blog.dto.PostDto;
import com.example.Spring.Boot.Blog.dto.PostResponse;
import com.example.Spring.Boot.Blog.exception.ResourceNotFoundException;
import com.example.Spring.Boot.Blog.model.Category;
import com.example.Spring.Boot.Blog.model.Post;
import com.example.Spring.Boot.Blog.repository.CategoryRepository;
import com.example.Spring.Boot.Blog.repository.PostRepository;
import com.example.Spring.Boot.Blog.service.PostService;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           ModelMapper mapper, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //fetch Categories
        Category category = categoryRepository.findById(postDto.getCategoryId()).get();
        //convert DTO to entity;
        Post post = mapToEntity(postDto);

        post.setCategory(category);
        Post postCreated = postRepository.save(post);

        //convert entity to DTO
        PostDto postResponse = mapToDto(postCreated);
        return postResponse;
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        //Getting the sort direction
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        //create Pageable Instances
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);

        //Get all pages
        List<Post> allPosts = posts.getContent();
        List<PostDto> content = allPosts.stream().map(t -> mapToDto(t)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
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
        if (!post.isPresent())
            throw new ResourceNotFoundException("post", "id", id);
        return mapToDto(post.get());
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long id) {
        Post fetchedPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        //fetch Categories
        Category category = categoryRepository.findById(postDto.getCategoryId()).get();
        //convert to entity
        Post updatedPost = mapToEntity(postDto);
        //update the old with new
        fetchedPost.setTitle(updatedPost.getTitle());
        fetchedPost.setDescription(updatedPost.getDescription());
        fetchedPost.setContent(updatedPost.getContent());
        fetchedPost.setCategory(category);
        //save it
        Post newlyUpdatedAndSavedPost = postRepository.save(fetchedPost);
        return mapToDto(newlyUpdatedAndSavedPost);
    }

    @Override
    public void deletePostById(long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) postRepository.deleteById(id);
        else throw new ResourceNotFoundException("post", "id", id);
    }

    @Override
    public CategoryDto getCategoryByPost(long postId) {
        Post fetchedPost = postRepository.findById(postId).get();
        Category categories = fetchedPost.getCategory();
        return mapper.map(categories, CategoryDto.class);
    }

    /**
     * @param categoryId
     * @return
     */
    @Override
    public List<PostDto> getAllPostsByCategoryId(long categoryId) {
        Optional<Category> fetchedCategory=categoryRepository.findById(categoryId);
        if(!fetchedCategory.isPresent()) throw  new ResourceNotFoundException("Category","id",categoryId);

        List<Post> allFetchedPosts = postRepository.findByCategoryId(categoryId);
        return allFetchedPosts.stream().map(post -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    private PostDto mapToDto(Post post) {
        PostDto postDto = mapper.map(post, PostDto.class);
        return postDto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post post = mapper.map(postDto, Post.class);
        return post;
    }
}
