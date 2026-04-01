package com.springboot.myapp.config;

import com.springboot.myapp.exception.ResourceNotFoundException;
import com.springboot.myapp.exception.TicketUpdatePermissionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> getMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String,Object> map = new HashMap<>();
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> list = bindingResult.getFieldErrors();

        for(FieldError fieldError : list){
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(map);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> getMethodArgumentNotValidException(HttpMessageNotReadableException e){
        Map<String,Object> map = new HashMap<>();
        map.put("message","Invalid enum value");
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(map);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> getResourceNotFoundException(ResourceNotFoundException e){
        Map<String,Object> map = new HashMap<>();
        map.put("message",e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }

    @ExceptionHandler(TicketUpdatePermissionException.class)
    public ResponseEntity<?> handleTicketUpdatePermissionException(
            TicketUpdatePermissionException e
    ){
        Map<String,Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.PRECONDITION_FAILED)
                .body(map);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e
    ){
        Map<String,Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(map);
    }
}