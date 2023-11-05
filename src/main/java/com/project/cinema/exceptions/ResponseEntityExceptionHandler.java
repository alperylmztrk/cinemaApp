package com.project.cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ResponseEntityExceptionHandler {

    @ExceptionHandler(value = MovieException.class)
    protected ResponseEntity<?> handleMovieException(MovieException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getSuccess());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


}
