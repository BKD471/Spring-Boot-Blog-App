package com.example.Spring.Boot.Blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;

    @NotEmpty(message = "Name should not be Null or Empty")
    private String name;

    @NotEmpty(message="Email should not be Null or Empty")
    @Email
    private String email;

    @NotEmpty
    @Size(min=10,message="Comment should be at least  greater than 10 characters")
    private String body;
}
