package ru.ne1ghost.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.ne1ghost.blog.config.CustomUserDetails;
import ru.ne1ghost.blog.entities.Post;
import ru.ne1ghost.blog.services.PostService;
import ru.ne1ghost.blog.services.UserService;

import java.util.Date;
import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    /*
    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
    */

    @GetMapping(value = "/posts")
    public List<Post> posts(){
        return postService.getAllPosts();
    }

    @PostMapping(value = "/post")
    public String publishPost(@RequestBody Post post) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(post.getDateCreated() == null)
            post.setDateCreated(new Date());
        post.setCreator(userService.getUser(userDetails.getUsername()));
        postService.insert(post);
        return "Post was published";
    }

    @GetMapping(value = "/posts/{username}")
    public List<Post> postsByUserame(@PathVariable String username){
        return postService.findByUser(userService.getUser(username));
    }
}
