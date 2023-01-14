package com.example.Spring.Boot.Blog.service;

import com.example.Spring.Boot.Blog.dto.LoginDto;
import com.example.Spring.Boot.Blog.dto.SignUpDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String SignUp(SignUpDto signUpDto);
}
