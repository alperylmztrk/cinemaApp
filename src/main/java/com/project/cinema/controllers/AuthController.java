package com.project.cinema.controllers;

import com.project.cinema.dto.response.LoginResDto;
import com.project.cinema.security.enums.Role;
import com.project.cinema.security.jwtauth.dto.AuthReq;
import com.project.cinema.services.JwtService;
import com.project.cinema.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("generate-token")
    public LoginResDto generateToken(@RequestBody AuthReq authReq) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.username(), authReq.password()));
        if (authentication.isAuthenticated()) {
            var yetkiler=userService.getUserByUsername(authReq.username()).getAuthorities().stream().map(Role::getValue).collect(Collectors.toSet());
            return LoginResDto.builder()
                    .token(jwtService.generateToken(authReq.username()))
                    .authorities(yetkiler)
                    .build();
        }
        throw new UsernameNotFoundException("User not found " + authReq.username());
    }

    @GetMapping("register")
    public String getRegister() {
        return "Auth controller register";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "Auth controller login";
    }

    @GetMapping("/user")
    public String getUser() {
        return "Auth controller user";
    }

    @GetMapping("admin")
    public String getAdmin() {
        return "Auth controller admin";
    }


}
