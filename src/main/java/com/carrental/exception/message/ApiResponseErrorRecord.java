package com.carrental.exception.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiResponseErrorRecord(
        HttpStatus status,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime timestamp,
        String message,
        String path) {


    public ApiResponseErrorRecord(HttpStatus httpStatus) {
        this(httpStatus, LocalDateTime.now(), "Unexpected error occurred.", null);
    }

    public ApiResponseErrorRecord(HttpStatus httpStatus, String message, String path){
        this(httpStatus, LocalDateTime.now(), message, path);
    }

    public ApiResponseErrorRecord(HttpStatus httpStatus, String message){
        this(httpStatus, LocalDateTime.now(), message, null);
    }
}
