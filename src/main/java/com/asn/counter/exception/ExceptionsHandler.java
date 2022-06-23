package com.asn.counter.exception;

import java.time.Instant;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException e,
                                                                      HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(Instant.now().toEpochMilli())
                        .message(e.getMessage())
                        .method(request.getMethod())
                        .path(request.getRequestURI())
                        .build());
    }
}
