package com.example.Spring.Boot.Blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
    public  String getString(){
        return "Hello Sprint Rest Services..";
    }

}
