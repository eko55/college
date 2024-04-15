package com.example.college.controller;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.model.dto.UserCreationRequest;
import com.example.college.model.entity.User;
import com.example.college.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody UserCreationRequest requestBody, UriComponentsBuilder builder) {
        User user;
        try {
            user = userService.createUser(requestBody);
        } catch (ResourceAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        URI newUserLocation = builder
                .path("users/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(newUserLocation).body(user);
    }
}
