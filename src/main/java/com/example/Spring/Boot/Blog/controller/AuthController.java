package com.example.Spring.Boot.Blog.controller;

import com.example.Spring.Boot.Blog.dto.JwtAuthResponseDto;
import com.example.Spring.Boot.Blog.dto.LoginDto;
import com.example.Spring.Boot.Blog.dto.SignUpDto;
import com.example.Spring.Boot.Blog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AuthController {
   private final AuthService authService;

   public  AuthController(AuthService authService){
       this.authService=authService;
   }

   @PostMapping(value={"/v1/auth/login","/v1/auth/signin"})
    ResponseEntity<JwtAuthResponseDto> login(@RequestBody LoginDto loginDto){
       String token=authService.login(loginDto);
       JwtAuthResponseDto jwtAuthResponse=new JwtAuthResponseDto();
       jwtAuthResponse.setAccessToken(token);
       return ResponseEntity.status(HttpStatus.OK).body(jwtAuthResponse);
    }

    @PostMapping(value={"/v1/auth/signup","/v1/auth/register"})
    ResponseEntity<String> register(@RequestBody SignUpDto signUpDto){
       String response=authService.SignUp(signUpDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}