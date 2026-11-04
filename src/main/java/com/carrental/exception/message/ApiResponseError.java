package com.carrental.exception.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiResponseError {

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String URI;

    private ApiResponseError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponseError(HttpStatus status) {
        this();
        this.status = status;
        this.message = "Unexpected error occurred.";
    }

    public ApiResponseError(HttpStatus status, String message, String URI) {
        this(status);
        this.message = message;
        this.URI = URI;

    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    @Override
    public String toString() {
        return "ApiResponseError{" +
                "status=" + status +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", URI='" + URI + '\'' +
                '}';
    }
}
