package com.example.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello from Merged Branches!";
    }

    @GetMapping("/api/users")
    public String getUsers() {
        return "User list from REST API branch";
    }

    @GetMapping("/api/status")
    public String getStatus() {
        return "Status: REST API is fully operational";
    }

    @GetMapping("/api/backend")
    public String getBackendInfo() {
        return "Backend REST API information";
    }

    @GetMapping("/api/frontend")
    public String getFrontendData() {
        return "Frontend data from UI branch";
    }
}
