package edu.pict.authservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {
    @GetMapping("/")
    public ResponseEntity<String> dummy() {
        return ResponseEntity.status(HttpStatus.OK).body("This Service is Alive");
    }
}
