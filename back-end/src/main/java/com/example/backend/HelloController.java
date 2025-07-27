package com.example.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello from Feature Branch!";
    }

    @GetMapping("/api/users")
    public String getUsers() {
        return "User list from feature branch";
    }

    @GetMapping("/api/status")
    public String getStatus() {
        return "Status: Backend is running on feature branch";
    }
}
