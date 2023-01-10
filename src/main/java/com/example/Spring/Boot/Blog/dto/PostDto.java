package com.example.Spring.Boot.Blog.dto;

import lombok.Data;
import java.util.Set;

@Data
public class PostDto {

    private Long id;
    private String title;
    private  String description;
    private String content;
    private Set<CommentDto> comments;
}
