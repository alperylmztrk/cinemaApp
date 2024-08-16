package com.project.cinema.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ExpiredJwtException.class)
    protected ResponseEntity<?> handleExpiredJWTException(ExpiredJwtException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("Tokenin süresi "+ ex.getClaims().getExpiration() +"tarihinde doldu, tekrar giriş yapınız.",false);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(value = MovieException.class)
    protected ResponseEntity<?> handleMovieException(MovieException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getSuccess());
        return ResponseEntity.status(ex.getHttpStatusCode()).body(errorResponse);
    }


}
