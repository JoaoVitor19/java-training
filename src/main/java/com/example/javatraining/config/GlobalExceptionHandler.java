package com.example.javatraining.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidNullException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Customize the error response as needed
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
    }

    @ExceptionHandler(InvalidNullException.class)
    public ResponseEntity<String> handleNullException(Exception e) {
        // Customize the error response as needed
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Null Exception: " + e.getMessage());
    }

    // Add more @ExceptionHandler methods for handling specific exception types if needed
}