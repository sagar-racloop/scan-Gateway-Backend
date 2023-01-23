package com.abdm.sharegateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scan-gateway")
@Slf4j
public class TestController {

    @GetMapping("/ping")
    ResponseEntity<String> test(@RequestBody String object) {
        log.info(object);
        return ResponseEntity.ok("Pong from Scan-gateway!");
    }
}
