package com.example.modsen.controller;

import com.example.modsen.exception.EmptyResultControllerException;
import com.example.modsen.exception.RequestEntityControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = RestController.class)
public class ControllerHandlerExceptionAdvice{

    @ExceptionHandler({EmptyResultControllerException.class})
    public ResponseEntity<Object> handleException(EmptyResultControllerException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("errorMessage", e.getMessage());
        response.put("errorCode", HttpStatus.NOT_FOUND.value());
//        response.put("request", request);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RequestEntityControllerException.class})
    public ResponseEntity<Object> handleException(RequestEntityControllerException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("errorMessage", e.getMessage());
        response.put("errorCode", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleException(HttpMessageNotReadableException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("errorMessage", e.getMessage());
        response.put("errorCode", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleException(ConstraintViolationException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("errorMessage", e.getMessage());
        response.put("errorCode", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
