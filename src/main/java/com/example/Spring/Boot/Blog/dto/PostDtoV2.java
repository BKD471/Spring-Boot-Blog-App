package com.example.Spring.Boot.Blog.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class PostDtoV2 {

    private Long id;
    @NotEmpty
    @Size(min=2, message = "Post title should be greater than 2 characters")
    private String title;

    @NotEmpty
    @Size(min=10,message = "Description should atleast be 10 characters")
    private  String description;

    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
    private Long categoryId;
    private List<String> tags;
}
