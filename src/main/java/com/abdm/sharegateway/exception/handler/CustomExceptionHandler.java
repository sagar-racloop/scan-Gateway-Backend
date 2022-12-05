package com.abdm.sharegateway.exception.handler;

import com.abdm.sharegateway.exception.NoStackException;
import com.abdm.sharegateway.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoStackException.class)
    public final ResponseEntity<Object> handleNoStackException(NoStackException ex) {
        ExceptionResponse response = new ExceptionResponse(new Date().getTime(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(NoStackException ex) {
        ExceptionResponse response = new ExceptionResponse(new Date().getTime(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
