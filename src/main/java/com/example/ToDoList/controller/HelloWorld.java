package com.example.ToDoList.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class HelloWorld {
    @GetMapping
    public ResponseEntity getHello() {
        try {
            return ResponseEntity.ok("Hello World");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/api")
    public ResponseEntity getApi() {
        try {
            return ResponseEntity.ok("API is working");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
