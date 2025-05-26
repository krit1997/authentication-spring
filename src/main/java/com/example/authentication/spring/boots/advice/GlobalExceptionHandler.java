// package com.example.authentication.spring.boots.advice;

// import java.util.NoSuchElementException;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import
// org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

// import com.example.authentication.spring.boots.responses.ApiResponse;

// @ControllerAdvice
// public class GlobalExceptionHandler {

// @ExceptionHandler(NoSuchElementException.class)
// public ResponseEntity<ApiResponse<Object>>
// handleNotFound(NoSuchElementException ex) {
// return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
// }

// @ExceptionHandler(MethodArgumentTypeMismatchException.class)
// public ResponseEntity<ApiResponse<Object>>
// handleBadRequest(MethodArgumentTypeMismatchException ex) {
// return buildResponse(HttpStatus.BAD_REQUEST, "Invalid parameter type");
// }

// @ExceptionHandler(Exception.class)
// public ResponseEntity<ApiResponse<Object>> handleGeneral(Exception ex) {
// return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
// }

// private ResponseEntity<ApiResponse<Object>> buildResponse(HttpStatus status,
// String message) {
// ApiResponse<Object> response = new ApiResponse<>(false, status.value(),
// message, null);
// return new ResponseEntity<>(response, status);
// }
// }
