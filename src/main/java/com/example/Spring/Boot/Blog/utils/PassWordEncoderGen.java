package com.example.Spring.Boot.Blog.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PassWordEncoderGen {
    public static void main(String[] args) {
        PasswordEncoder ps=new BCryptPasswordEncoder();
        //System.out.println(ps.encode("bhaskar"));
        //System.out.println(ps.encode("admin"));
    }
}
