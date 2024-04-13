package com.example.college.exception;

public class ResourceAlreadyExistsException extends RuntimeException{

    private String message;

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
