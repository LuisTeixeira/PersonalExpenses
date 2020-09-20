package com.lmteixeira.personalfinances.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public ResponseEntity<List<Object>> getAllUsers() {
        return ResponseEntity.ok(new ArrayList<Object>());
    }

    @PostMapping("/users")
    public ResponseEntity createUser() {
        return ResponseEntity.status(422).build();
    }

}
