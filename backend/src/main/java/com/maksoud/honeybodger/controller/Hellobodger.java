package com.maksoud.honeybodger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hellobodger {
    @GetMapping("/api/hello")
    public String sayHello() {
        return "Hello from Java Backend!";
    }
}