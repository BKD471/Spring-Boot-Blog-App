package com.example.Spring.Boot.Blog.exception;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogApiException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
