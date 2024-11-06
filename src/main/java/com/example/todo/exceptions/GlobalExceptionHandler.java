package com.example.todo.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final String DEFAULT_SYSTEM_ERROR_MESSAGE = "An error occurred while processing your request. Please contact the system administrator";

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

    @ExceptionHandler(value = {DuplicateRecordException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    ResponseEntity<ApiException> handleDuplicateRecordException(DuplicateRecordException ex) {
        final ApiException apiException =new ApiException(ex.getMessage(), HttpStatus.CONFLICT,ZonedDateTime.now(ZoneId.systemDefault()));
        return buildResponseEntity(apiException);

    }

    @ExceptionHandler(value = {RecordNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ApiException> handleRecordNotFoundException(RecordNotFoundException ex) {
        final ApiException apiException =new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND,ZonedDateTime.now(ZoneId.systemDefault()));
        return buildResponseEntity(apiException);

    }


    @ExceptionHandler(value = {SystemException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ApiException> handleSystemException(SystemException ex) {
        final ApiException apiException =new ApiException(DEFAULT_SYSTEM_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR,ZonedDateTime.now(ZoneId.systemDefault()));
        return buildResponseEntity(apiException);

    }

    private ResponseEntity<ApiException> buildResponseEntity(ApiException apiException) {
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
}
