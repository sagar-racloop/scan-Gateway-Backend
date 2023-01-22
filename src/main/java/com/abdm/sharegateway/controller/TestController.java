package com.abdm.sharegateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scan-gateway")
public class TestController {

    @GetMapping("/ping")
    ResponseEntity<String> test() {
        return ResponseEntity.ok("Pong from Scan-gateway!");
    }
}
