package com.carrental.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "tbl_contact_message")
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Size(min = 1, max = 50, message = "Your name '${validatedValue}' must be between {min} and {max} chars long.")
    @NotNull(message = "Please type your name.")
    @Column(length = 50, nullable = false)
    private String senderName;

    @Size(min = 5, max = 50, message = "Message title '${validatedValue}' must be between {min} and {max} chars long.")
    @NotNull(message = "Please type message title.")
    @Column(length = 50, nullable = false)
    private String messageTitle;

    @Size(min = 20, max = 200, message = "Message body '${validatedValue}' must be between {min} and {max} chars long.")
    @NotNull(message = "Please type message body.")
    @Column(length = 200, nullable = false)
    private String messageBody;

    @Column(length = 50, nullable = false)
    @NotNull(message = "Please type email.")
    @Email(message = "Please type valid email.")
    private String email;
}
