package com.example.Spring.Boot.Blog.exception;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class BlogApiException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
