package com.landgraf.users.infrastructure.exceptions;


import com.landgraf.users.infrastructure.exceptions.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildError(HttpStatus.NOT_FOUND.value(),
                resourceNotFoundException.getMessage(),
                "Not Found",
                request.getRequestURI()));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseDTO> handleConflictException(ConflictException conflictException, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildError(HttpStatus.CONFLICT.value(),
                conflictException.getMessage(),
                "Conflict",
                request.getRequestURI()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponseDTO> handleUnauthorizedException(UnauthorizedException unauthorizedException, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildError(HttpStatus.CONFLICT.value(),
                unauthorizedException.getMessage(),
                "Conflict",
                request.getRequestURI()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildError(HttpStatus.BAD_REQUEST.value(),
                illegalArgumentException.getMessage(),
                "Bad Request",
                request.getRequestURI()));
    }

    @ExceptionHandler(InvalidZipCodeException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidZipCodeException(InvalidZipCodeException invalidZipCodeException, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildError(HttpStatus.BAD_REQUEST.value(),
                invalidZipCodeException.getMessage(),
                "Bad Request",
                request.getRequestURI()));
    }

    private ErrorResponseDTO buildError(int status, String error, String message, String path) {
        return  ErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .message(message)
                .status(status)
                .error(error)
                .path(path)
                .build();
    }
}
