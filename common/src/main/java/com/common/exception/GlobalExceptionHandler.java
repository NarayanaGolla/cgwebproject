package com.common.exception;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  //    it handles all order exception
  @ExceptionHandler(OrderException.class)
  public ResponseEntity<String> handleOrderException(OrderException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  //    it handles all taco exception
  @ExceptionHandler(TacoNotFoundException.class)
  public ResponseEntity<String> handleTacoNotFoundException(TacoNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  //    it handles all ingredient exception
  @ExceptionHandler(IngredientException.class)
  public ResponseEntity<String> handleIngredientException(IngredientException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGeneralException(Exception ex) {
    return new ResponseEntity<>(
        "Something went wrong: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Handle JWT/auth related issues
  @ExceptionHandler(SecurityException.class)
  public ResponseEntity<Map<String, Object>> handleSecurity(SecurityException ex) {
    return buildErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
  }

  // Handle validation errors
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
    return buildErrorResponse(HttpStatus.BAD_REQUEST, "Validation failed");
  }

  // Fallback for any unhandled exceptions
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
    return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
  }

  private ResponseEntity<Map<String, Object>> buildErrorResponse(
      HttpStatus status, String message) {
    Map<String, Object> errorBody = new HashMap<>();
    errorBody.put("timestamp", LocalDateTime.now());
    errorBody.put("status", status.value());
    errorBody.put("error", status.getReasonPhrase());
    errorBody.put("message", message);
    return new ResponseEntity<>(errorBody, status);
  }
}
