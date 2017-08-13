package ru.ne1ghost.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import ru.ne1ghost.blog.entities.Role;
import ru.ne1ghost.blog.entities.User;
import ru.ne1ghost.blog.pojos.UserRegistration;
import ru.ne1ghost.blog.services.UserService;


import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public String register(@RequestBody UserRegistration userRegistration){
        if(!(userRegistration.getPassword().equals(userRegistration.getPasswordConfirmation())))
            return "password o not match";
        else if (userService.getUser(userRegistration.getUsername()) != null)
            return "user already exists";

        userService.save(new User(userRegistration.getUsername(),userRegistration.getPassword(), Arrays.asList(new Role("User"))));
        return "User created";
    }



    @GetMapping(value = "/users")
    public List<User> users(){
        return userService.getAllUsers();
    }


    @Autowired
    private TokenStore tokenStore;

    @GetMapping(value = "/logouts")
    public void logout(@RequestParam (value = "access_token") String accessToken){
        tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
    }

    @GetMapping(value = "/getUsername")
    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
