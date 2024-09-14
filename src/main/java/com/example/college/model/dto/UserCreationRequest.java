package com.example.college.model.dto;

import com.example.college.model.Role;

public record UserCreationRequest(
    String username,
    String password,
    String email,
    Role role
){}
