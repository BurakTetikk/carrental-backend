package com.carrental.dto.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterPayload(
        @Size(max = 50) @NotBlank(message = "Please type your first name.") String firstName,
        @Size(max = 50) @NotBlank(message = "Please type your last name.") String lastName,
        @Size(min = 5, max = 80) @NotBlank(message = "Please type your first name.") @Email(message = "Please type valid email.") String email,
        @Size(min = 4, max = 20, message = "Please type correct size of password: minimum: {min} and maximum: {max}.") @NotBlank(message = "Please type your first name.") String password,
        @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", //(541) 317-8828
                message = "Please type valid phone number.")
        @Size(min = 14, max = 14)
        @NotBlank(message = "Please type your phone number.") String phoneNumber,
        @Size(max = 100) @NotBlank(message = "Please type your address.") String address,
        @Size(max = 15) @NotBlank(message = "Please type your zip code.") String zipCode
) {
}
