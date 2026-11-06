package com.carrental;

import com.carrental.exception.message.ApiResponseError;
import com.carrental.exception.message.ApiResponseErrorRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class CarrentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarrentalApplication.class, args);
		ApiResponseError apiResponseError = new ApiResponseError(HttpStatus.NOT_FOUND);
		ApiResponseErrorRecord apiResponseErrorRecord = new ApiResponseErrorRecord(HttpStatus.CREATED, "sıkıntı");
		System.out.println(apiResponseErrorRecord.toString());
		System.out.println(apiResponseError.toString());
	}

}
