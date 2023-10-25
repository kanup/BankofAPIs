package com.example.login.controller;

import com.example.login.model.User;
import com.example.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User u){
        return userService.addUser(u);
    }

    @PutMapping("/changePassword")
    public User changePassword(@RequestBody User u){
        return userService.changePassword(u);
    }
}
