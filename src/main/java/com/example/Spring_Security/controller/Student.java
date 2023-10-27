package com.example.Spring_Security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class Student {
    @GetMapping("/welcome")
    public String welocome(){
        return "Welcome to Student!!!";
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello to Student!!!";
    }
}
