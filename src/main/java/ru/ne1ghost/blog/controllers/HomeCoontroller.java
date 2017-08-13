package ru.ne1ghost.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeCoontroller {
    @GetMapping(value = "/")
    public String index(){
        return "index";
    }
    @GetMapping(value="/login")
    public String login(){
        return "login";
    }
}
