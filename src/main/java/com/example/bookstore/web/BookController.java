package com.example.bookstore.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/index")
    public String index() {
        return "Welcome to the Bookstore";
    }
}
