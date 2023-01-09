package com.example.Spring.Boot.Blog.service.impl;

import com.example.Spring.Boot.Blog.dto.CommentDto;
import com.example.Spring.Boot.Blog.exception.BlogApiException;
import com.example.Spring.Boot.Blog.exception.ResourceNotFoundException;
import com.example.Spring.Boot.Blog.model.Comments;
import com.example.Spring.Boot.Blog.model.Post;
import com.example.Spring.Boot.Blog.repository.CommentRepository;
import com.example.Spring.Boot.Blog.repository.PostRepository;
import com.example.Spring.Boot.Blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        // Convert DTo to entity
        Comments comments = mapToEntity(commentDto);
        //Fetching Post by id
        Post fetchedPost = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));

        comments.setPost(fetchedPost);
        //save comments to repository
        Comments savedComment = commentRepository.save(comments);
        //map to Dto
        return mapToDto(savedComment);

    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {
        //Fetch All Comments by PostId
        List<Comments> allFetchedComments = commentRepository.findByPostId(postId);
        if (allFetchedComments.size() == 0) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, String.format("No such posts found with id =%s", postId));
        }
        return allFetchedComments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    public Comments fetchComment(long postId, long commentId) {
        //Fetch All comments by PostId
        List<Comments> allFetchedComments = commentRepository.findByPostId(postId);
        if (allFetchedComments.size() == 0) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, String.format("Post not found with id %s", postId));
        }
//Check whether any comment with id given is present in all the comments list fetched for the post
        List<Comments> getCommentById = allFetchedComments.stream().filter(comment -> comment.getId() == commentId).collect(Collectors.toList());
        if (getCommentById.size() == 0) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,
                    String.format("No Comment with id =%s found for the post with id =%s", commentId, postId));
        }
        return getCommentById.get(0);
    }

    @Override
    public CommentDto getCommentByCommentId(long postId, long commentId) {
        Comments comment=fetchComment(postId,commentId);
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
        //Fetching Old Comment
        Comments comments=fetchComment(postId,commentId);
        //New Comment
        Comments newComment = mapToEntity(commentDto);

        //Updating the old comment with new details
        comments.setName(newComment.getName());
        comments.setBody(newComment.getBody());
        comments.setEmail(newComment.getEmail());

        //Saving the updated comment to db
        Comments newlySavedComments = commentRepository.save(comments);
        return mapToDto(newlySavedComments);
    }

    @Override
    public void deleteCommentById(long postId,long commentId){
        Comments comments=fetchComment(postId,commentId);
        commentRepository.delete(comments);
    }

    CommentDto mapToDto(Comments comments) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comments.getId());
        commentDto.setName(comments.getName());
        commentDto.setEmail(comments.getEmail());
        commentDto.setBody(comments.getBody());
        return commentDto;
    }

    Comments mapToEntity(CommentDto commentDto) {
        Comments comments = new Comments();
        comments.setId(commentDto.getId());
        comments.setName(commentDto.getName());
        comments.setEmail(commentDto.getEmail());
        comments.setBody(commentDto.getBody());
        return comments;
    }
}
