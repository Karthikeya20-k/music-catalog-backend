package com.karthik.musiccatalog.controller;

import com.karthik.musiccatalog.dto.*;
import com.karthik.musiccatalog.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){

        return service.register(request);

    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){

        return service.login(request);

    }

}