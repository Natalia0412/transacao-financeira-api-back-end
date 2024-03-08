package com.tgid.teste.junior.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestControllerAdviceHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ResourceConflictException.class)
    public GenericResponseErrorMsg handleResourceConflict(ResourceConflictException ex, HttpServletRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
        List<ErrorMsg> errorMsgs = new ArrayList<>();

        ErrorMsg errorMsg = ErrorMsg.builder()
                .message(ex.getMessage())
                .build();
        errorMsgs.add(errorMsg);
        return  GenericResponseErrorMsg.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .errors(errorMsgs)
                .path(request.getRequestURI())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public GenericResponseErrorMsg handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<ErrorMsg> errorMsgs = new ArrayList<>();

        ErrorMsg errorMsg = ErrorMsg.builder()
                .message(ex.getMessage())
                .build();
        errorMsgs.add(errorMsg);
        return  GenericResponseErrorMsg.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .errors(errorMsgs)
                .path(request.getRequestURI())
                .build();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ResourceInsufficientException.class)
    public GenericResponseErrorMsg handleResourceInsufficient(ResourceInsufficientException ex, HttpServletRequest request){
        HttpStatus status = HttpStatus.FORBIDDEN;
        List<ErrorMsg> errorMsgs = new ArrayList<>();

        ErrorMsg errorMsg = ErrorMsg.builder()
                .message(ex.getMessage())
                .build();
        errorMsgs.add(errorMsg);
        return  GenericResponseErrorMsg.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .errors(errorMsgs)
                .path(request.getRequestURI())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericResponseErrorMsg handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ){
        List<ErrorMsg> errorMsgs = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error
        )->{
            ErrorMsg errorMsg = ErrorMsg.builder()
                    .field(((FieldError) error).getField())
                    .message(error.getDefaultMessage())
                    .build();
            errorMsgs.add(errorMsg);
        });
        return GenericResponseErrorMsg.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errorMsgs)
                .path(request.getRequestURI())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler( HttpMessageNotReadableException.class)
    public GenericResponseErrorMsg handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<ErrorMsg> errorMsgs = new ArrayList<>();

        String customErrorMessage = "Required field is invalid.";
        ErrorMsg errorMsg = ErrorMsg.builder()
                .message(customErrorMessage)
                .build();
        errorMsgs.add(errorMsg);
        return  GenericResponseErrorMsg.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .errors(errorMsgs)
                .path(request.getRequestURI())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResourceMailSendException.class)
    public GenericResponseErrorMsg handleMailSend(ResourceMailSendException ex, HttpServletRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        List<ErrorMsg> errorMsgs = new ArrayList<>();
        ErrorMsg errorMsg = ErrorMsg.builder()
                .message(ex.getMessage())
                .build();
        errorMsgs.add(errorMsg);
        return  GenericResponseErrorMsg.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .errors(errorMsgs)
                .path(request.getRequestURI())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public GenericResponseErrorMsg handleGenericException(Exception ex , HttpServletRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        List<ErrorMsg> errorMsgs = new ArrayList<>();
        ErrorMsg errorMsg = ErrorMsg.builder()
                .message(ex.getMessage())
                .build();
        errorMsgs.add(errorMsg);
        return  GenericResponseErrorMsg.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .errors(errorMsgs)
                .path(request.getRequestURI())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public GenericResponseErrorMsg handleGenericRuntimeException(RuntimeException ex, HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        List<ErrorMsg> errorMsgs = new ArrayList<>();
        ErrorMsg errorMsg = ErrorMsg.builder()
                .message(ex.getMessage())
                .build();
        errorMsgs.add(errorMsg);
        return  GenericResponseErrorMsg.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .errors(errorMsgs)
                .path(request.getRequestURI())
                .build();
    }

}
