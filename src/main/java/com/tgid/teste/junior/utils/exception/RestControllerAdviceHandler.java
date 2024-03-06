package com.tgid.teste.junior.utils.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class RestControllerAdviceHandler {

    @ExceptionHandler(ResourceConflict.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public RespondErrorMsg resourceConflictException(ResourceConflict ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        RespondErrorMsg err1 = RespondErrorMsg.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .errors(List.of(ErrorMsg.builder().message(ex.getMessage()).build()))
                .path(request.getRequestURI())
                .build();
        return err1;
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public RespondErrorMsg resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        RespondErrorMsg err1 = RespondErrorMsg.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .errors(List.of(ErrorMsg.builder().message(ex.getMessage()).build()))
                .path(request.getRequestURI())
                .build();
        return err1;
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RespondErrorMsg insufficientBalanceException(InsufficientBalanceException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        RespondErrorMsg err1 = RespondErrorMsg.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .errors(List.of(ErrorMsg.builder().message(ex.getMessage()).build()))
                .path(request.getRequestURI())
                .build();
        return err1;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RespondErrorMsg handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        List<ErrorMsg> errorMsgs = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            ErrorMsg errorMsg = ErrorMsg.builder()
                    .field(((FieldError) error).getField())
                    .message(error.getDefaultMessage())
                    .build();
            errorMsgs.add(errorMsg);
        });

        return RespondErrorMsg.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errorMsgs)
                .path(request.getRequestURI())
                .build();
    }
}
