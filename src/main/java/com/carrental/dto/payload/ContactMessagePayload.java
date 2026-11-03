package com.carrental.dto.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ContactMessagePayload(
        @NotBlank(message = "Please type your name.") @Size(min = 1, max = 50, message = "Your name '${validatedValue}' must be between {min} and {max} chars long.") String senderName,
        @Size(min = 5, max = 50, message = "Message title must be between {min} and {max} chars long.")
        @NotBlank(message = "Please type message title.") String messageTitle,
        @Size(min = 20, max = 200, message = "Message body must be between {min} and {max} chars long.")
        @NotBlank(message = "Please type message body.") String messageBody,
        @NotBlank(message = "Please type email.")
        @Email(message = "Please type valid email.") String email) {
}
