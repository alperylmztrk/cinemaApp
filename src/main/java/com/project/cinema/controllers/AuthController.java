package com.project.cinema.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("register")
    public String getRegister() {
        return "Auth controller register";
    }

    @GetMapping("login")
    public String getLogin() {
        return "Auth controller login";
    }


}
