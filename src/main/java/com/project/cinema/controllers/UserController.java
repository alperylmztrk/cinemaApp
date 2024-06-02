package com.project.cinema.controllers;

import com.project.cinema.model.User;
import com.project.cinema.security.jwtauth.dto.SaveUserReq;
import com.project.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(String sicil) {
        System.out.println("sicil= " + sicil);
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody SaveUserReq newUser) {
        return userService.saveUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUserById(@PathVariable Long userId, @RequestBody User newUser) {
        return userService.updateUserById(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }

}
