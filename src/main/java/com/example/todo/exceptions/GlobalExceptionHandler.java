package com.example.todo.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiException> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request){
        return buildResponseEntity(new ApiException(
                ex.getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining(System.getProperty("line.separator"))),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.systemDefault())
        ));
    }

    @ExceptionHandler(value = {DateTimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ApiException> handleInvalidDateFormat(RuntimeException ex) {
        final ApiException apiException = new ApiException(ex.getMessage(), HttpStatus.BAD_REQUEST,ZonedDateTime.now(ZoneId.systemDefault()));
        return buildResponseEntity(apiException);

    }

    @ExceptionHandler(value = {ServletRequestBindingException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ApiException> handleInvalidRequestBinding(ServletException ex) {
        final ApiException apiException = new ApiException(ex.getMessage(), HttpStatus.BAD_REQUEST,ZonedDateTime.now(ZoneId.systemDefault()));
        return buildResponseEntity(apiException);

    }

    @ExceptionHandler(value = {InvalidFormatException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ApiException> handleInvalidFormatRequest(RuntimeException ex) {
        final ApiException apiException =new ApiException(ex.getMessage(), HttpStatus.BAD_REQUEST,ZonedDateTime.now(ZoneId.systemDefault()));
        return buildResponseEntity(apiException);

    }

    private ResponseEntity<ApiException> buildResponseEntity(ApiException apiException) {
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
}
