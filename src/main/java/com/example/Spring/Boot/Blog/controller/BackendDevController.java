package com.example.Spring.Boot.Blog.controller;


import com.example.Spring.Boot.Blog.beans.BackendDev;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BackendDevController {

    @GetMapping("/dev")
     public BackendDev sayDev(){
         BackendDev bd=new BackendDev("Bhaskar Kumar Das","Java Spring Boot",2000000l);
         return bd;
     }

     @GetMapping("/listOfDevs")
     List<BackendDev>  getListOfDevs(){
        List<BackendDev> lst=new ArrayList<>();
         BackendDev bd1=new BackendDev("Bhaskar Kumar Das","Java Spring Boot",2000000l);
         BackendDev bd2=new BackendDev("Lionel Messi","PHP Laravel",2200000l);
         BackendDev bd3=new BackendDev("Christiano Ronaldo","React Frontend",1100000l);
         lst.add(bd1);lst.add(bd2);lst.add(bd3);
         return lst;
     }
}
