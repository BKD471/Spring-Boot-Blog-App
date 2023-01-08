package com.example.Spring.Boot.Blog.service.impl;

import com.example.Spring.Boot.Blog.dto.CommentDto;
import com.example.Spring.Boot.Blog.model.Comments;
import com.example.Spring.Boot.Blog.model.Post;
import com.example.Spring.Boot.Blog.repository.CommentRepository;
import com.example.Spring.Boot.Blog.repository.PostRepository;
import com.example.Spring.Boot.Blog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository){
        this.commentRepository=commentRepository;
        this.postRepository=postRepository;
    }
    @Override
    public CommentDto createComment(CommentDto commentDto, long id){
        // Convert DTo to entity
        Comments comments=mapToEntity(commentDto);
        //Fetching Post by id
        Optional<Post> post=postRepository.findById(id);

        if(post.isPresent()){
            Post fetchedPost=post.get();
            comments.setPost(fetchedPost);
            //save comments to repository
            Comments savedComment=commentRepository.save(comments);
            //map to Dto
            return mapToDto(savedComment);
        }
        return null;
    }

    CommentDto mapToDto(Comments comments){
        CommentDto commentDto=new CommentDto();
        commentDto.setId(comments.getId());
        commentDto.setName(comments.getName());
        commentDto.setEmail(comments.getEmail());
        commentDto.setBody(comments.getBody());
        return commentDto;
    }
    Comments mapToEntity(CommentDto commentDto){
        Comments comments=new Comments();
        comments.setId(commentDto.getId());
        comments.setName(commentDto.getName());
        comments.setEmail(commentDto.getEmail());
        comments.setBody(commentDto.getBody());
        return  comments;
    }
}
