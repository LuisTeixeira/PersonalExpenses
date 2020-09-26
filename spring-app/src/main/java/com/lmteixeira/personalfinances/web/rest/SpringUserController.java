package com.lmteixeira.personalfinances.web.rest;

import com.lmteixeira.personalfinances.usecases.exceptions.UserNotFoundException;
import com.lmteixeira.personalfinances.web.controller.UserController;
import com.lmteixeira.personalfinances.web.models.UserUiModel;
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
    public ResponseEntity<List<UserUiModel>> getAllUsers() {
        List<UserUiModel> allUsers = userController.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping("/users")
    public ResponseEntity<UserUiModel> createUser(@Valid @RequestBody UserUiModel user) {
        userController.createUser(user);
        return ResponseEntity.status(201).body(user);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<UserUiModel> getUserByEmail(@PathVariable  String email) {
        try {
            UserUiModel user = userController.getUserByEmail(email);
            return ResponseEntity.status(200).body(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
