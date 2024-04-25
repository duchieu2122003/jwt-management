package com.example.server.infrastructure.exception;

import com.example.server.model.response.ApiErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
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
            log.error("==========RestApiException========== " + e.getMessage());
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse(e.getMessage());
            return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
        } else if (e instanceof MethodArgumentNotValidException) {
            log.error("==========MethodArgumentNotValidException========== " + e.getMessage());
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            String missionsString = String.join(", ", errors);
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse("" + missionsString);
            return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
        } else if (e instanceof RuntimeException) {
            log.error("==================RuntimeException================ " + e.getMessage());
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse(e.getMessage());
            return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
        } else {
            log.error("==================Internal================ " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
        log.error("==================handleAccessDeniedException================ " + e.getMessage());
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse("Không có quyền truy cập");
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleNHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error("==================handleNHttpRequestMethodNotSupportedException================ " + ex.getMessage());

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse("Đường dẫn không hợp lệ 404");
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtExceptionException(HttpRequestMethodNotSupportedException ex) {
        log.error("==================handleExpiredJwtExceptionException================ " + ex.getMessage());

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse("Phiên đăng nhập đã hết hạn hoặc lỗi hệ thống" + ex.getMessage());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


