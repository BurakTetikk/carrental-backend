package com.carrental.controller;

import com.carrental.dto.payload.RegisterPayload;
import com.carrental.dto.response.CarRentalResponse;
import com.carrental.security.jwt.JwtUtils;
import com.carrental.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class UserJwtController {

    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<CarRentalResponse> register(@Valid @RequestBody RegisterPayload registerPayload) {

        CarRentalResponse response = userService.saveUser(registerPayload);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

}
