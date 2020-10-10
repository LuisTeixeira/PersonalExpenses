package com.lmteixeira.personalfinances.spring.rest;

import com.lmteixeira.personalfinances.webadapter.controller.UserController;
import com.lmteixeira.personalfinances.webadapter.exception.UserNotFoundWebException;
import com.lmteixeira.personalfinances.webadapter.model.UserWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SpringUserController {

    private UserController userController;

    @Autowired
    public SpringUserController(UserController userController) {
        this.userController = userController;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserWeb>> getAllUsers() {
        List<UserWeb> allUsers = userController.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping("/users")
    public ResponseEntity<UserWeb> createUser(@Valid @RequestBody UserWeb user) {
        userController.createUser(user);
        return ResponseEntity.status(201).body(user);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<UserWeb> getUserByEmail(@PathVariable  String email) {
        try {
            UserWeb user = userController.getUserByEmail(email);
            return ResponseEntity.status(200).body(user);
        } catch (UserNotFoundWebException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
