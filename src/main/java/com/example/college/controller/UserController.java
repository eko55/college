package com.example.college.controller;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.UserCreationRequest;
import com.example.college.model.dto.UserRoleInput;
import com.example.college.model.entity.AppUser;
import com.example.college.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Users")
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AppUser> registerUser(@RequestBody UserCreationRequest requestBody, UriComponentsBuilder builder) {
        AppUser user;
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

    @Operation(summary = "Change user role")
    @PutMapping("/{userId}/roles")
    public ResponseEntity<Void> changeUserRole(@RequestParam Long userId, @RequestBody UserRoleInput role) {
        try {
            userService.changeRole(userId, role.getRole());
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get user")
    @GetMapping("/{userId}")
    public ResponseEntity<AppUser> getUser(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(userService.getUser(userId));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Operation(summary = "Get users")
    @GetMapping()
    public ResponseEntity<List<AppUser>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

//    @Operation(summary = "Change user password")
//    @PutMapping("/{userId}")
//    public ResponseEntity<Void> changeUserPassword(@RequestParam Long userId, @RequestBody UserPassChangeInput userInput) {
//        try {
//            userService.changeUserPassword(userId, userInput);
//        } catch (ResourceNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
//        } catch (OldPasswordNotMatchingException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
//        }
//        return ResponseEntity.noContent().build();
//    }
}
