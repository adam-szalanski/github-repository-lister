package com.example.github.repolister.githubcaller.exceptions;

import org.springframework.http.*;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String USER_NOT_FOUND = "User with provided credentials was not found";
    private static final String HEADER_NOT_ACCEPTABLE = "Provided header was not acceptable";

    @ExceptionHandler(RestClientException.class)
    protected ResponseEntity<ErrorDetails> handle404() {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), USER_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(errorDetails);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_ACCEPTABLE.value(), HEADER_NOT_ACCEPTABLE);
        return ResponseEntity.status(status).headers(headers).contentType(MediaType.APPLICATION_JSON).body(errorDetails.toString());
    }

}
