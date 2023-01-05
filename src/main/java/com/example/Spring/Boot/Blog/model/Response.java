package com.example.Spring.Boot.Blog.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
   private String statusCode;
   private String statusMessage;
}
