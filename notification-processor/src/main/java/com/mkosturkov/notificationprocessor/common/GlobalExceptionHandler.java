package com.mkosturkov.notificationprocessor.common;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleValidationError(MethodArgumentNotValidException exception) {
        var errorMessages = exception.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return ResponseEntity.badRequest()
                .body(new ErrorResponse(errorMessages));
    }

    public record ErrorResponse(List<String> errors) {
    }
}
