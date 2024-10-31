package com.goodinfluenceshop.controller;

import com.goodinfluenceshop.dto.ExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDto> handleIllegalArgumentException(IllegalArgumentException e) {
        ExceptionDto response = ExceptionDto.builder()
                .error("IllegalArgumentException")
                .message(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }
}
