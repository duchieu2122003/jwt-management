package com.example.server.infrastructure.exception;

import com.example.server.model.response.ApiErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author duchieu212
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handlerException(Exception e) {
        if (e instanceof RestApiException) {
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse(e.getMessage());
            return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
        } else if (e instanceof ConstraintViolationException) {
            log.error("==========ConstraintViolationException========== " + e.getMessage());
            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) e).getConstraintViolations();
            List<String> errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else if (e instanceof RuntimeException) {
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse(e.getMessage());
            return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
        } else {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Không có quyền truy cập");
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleNHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse("Đường dẫn không hợp lệ 404");
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }

}


