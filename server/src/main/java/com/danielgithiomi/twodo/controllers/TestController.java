package com.danielgithiomi.twodo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<TestResponse> test() {
        return ResponseEntity.status(200).body(new TestResponse("You have hit the test endpoint!", new Date()));
    }

    public record TestResponse(String message, Date timestamp) {
    }

}
