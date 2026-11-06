package com.carrental.exception;

import com.carrental.exception.message.ApiResponseError;
import com.carrental.exception.message.ApiResponseErrorRecord;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CarRentalExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(CarRentalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        ApiResponseError apiResponseError =
                new ApiResponseError(
                        HttpStatus.NOT_FOUND,
                        ex.getMessage(),
                        request.getDescription(false));

        //ApiResponseErrorRecord apiResponseError = new ApiResponseErrorRecord(HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false));

        return buildResponseEntity(apiResponseError);
    }


    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<@Nullable String> collect = ex
                .getAllErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .collect(Collectors.toList());

        //collect.forEach(System.out::println);

        ApiResponseErrorRecord apiResponseErrorRecord = new ApiResponseErrorRecord(
                HttpStatus.BAD_REQUEST,
                collect.getFirst(),
                request.getDescription(false)
        );

        return buildResponseEntity(apiResponseErrorRecord);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiResponseErrorRecord apiResponseErrorRecord = new ApiResponseErrorRecord(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getDescription(false)
        );

        return buildResponseEntity(apiResponseErrorRecord);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiResponseErrorRecord apiResponseErrorRecord = new ApiResponseErrorRecord(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                request.getDescription(false)
        );

        return buildResponseEntity(apiResponseErrorRecord);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiResponseErrorRecord apiResponseErrorRecord = new ApiResponseErrorRecord(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getDescription(false)
        );

        return buildResponseEntity(apiResponseErrorRecord);
    }


    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        ApiResponseErrorRecord apiResponseErrorRecord = new ApiResponseErrorRecord(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                request.getDescription(false)
        );

        return buildResponseEntity(apiResponseErrorRecord);
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        ApiResponseError apiResponseError =
                new ApiResponseError(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getMessage(),
                        request.getDescription(false));

        return buildResponseEntity(apiResponseError);
    }


    private ResponseEntity<Object> buildResponseEntity(ApiResponseError apiResponseError) {
        logger.error(apiResponseError.getMessage());
        return new ResponseEntity<>(apiResponseError, apiResponseError.getStatus());
    }

    private ResponseEntity<Object> buildResponseEntity(ApiResponseErrorRecord apiResponseErrorRecord) {
        logger.error(apiResponseErrorRecord.message());
        return new ResponseEntity<>(apiResponseErrorRecord, apiResponseErrorRecord.status());
    }


}
