package com.company;

import com.company.domain.error.RequestError;
import com.company.domain.error.ApiException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<RequestError> handleApiException(HttpServletRequest request, ApiException e) {
        return ResponseEntity.status(e.getRequestError().getHttpStatus()).contentType(MediaType.APPLICATION_JSON_UTF8).body(e.getRequestError());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<RequestError> handleException(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(RequestError.E1.getHttpStatus()).contentType(MediaType.APPLICATION_JSON_UTF8).body(RequestError.E1);
    }

}
