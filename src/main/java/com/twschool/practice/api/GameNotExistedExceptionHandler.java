package com.twschool.practice.api;

import com.twschool.practice.service.GameNotExistedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GameNotExistedExceptionHandler {
    @ExceptionHandler(value = GameNotExistedException.class)
    public ResponseEntity<Object> exception() {
        return new ResponseEntity<>("Please start new game", HttpStatus.BAD_REQUEST);
    }
}