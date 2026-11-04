package com.carrental.exception;

import com.carrental.exception.message.ApiResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CarRentalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        ApiResponseError apiResponseError =
                new ApiResponseError(
                        HttpStatus.NOT_FOUND,
                        ex.getMessage(),
                        request.getDescription(false));

        return buildResponseEntity(apiResponseError);
    }






    private ResponseEntity<Object> buildResponseEntity(ApiResponseError apiResponseError) {
        return new ResponseEntity<>(apiResponseError, apiResponseError.getStatus());
    }

}
