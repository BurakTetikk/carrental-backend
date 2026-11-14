package com.carrental.service;

import com.carrental.domain.Role;
import com.carrental.domain.User;
import com.carrental.domain.enums.RoleType;
import com.carrental.dto.payload.RegisterPayload;
import com.carrental.dto.response.CarRentalResponse;
import com.carrental.dto.response.CarrentalResponseMessage;
import com.carrental.exception.ConflictException;
import com.carrental.exception.ResourceNotFoundException;
import com.carrental.exception.message.ErrorMessage;
import com.carrental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
//@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleService roleService, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND, email))
                );
    }

    public CarRentalResponse saveUser(RegisterPayload registerPayload) {
        String email = registerPayload.email();
        if (userRepository.existsByEmail(email)) {
            throw new ConflictException(
                    String.format(ErrorMessage.EMAIL_CONFLICT_ERROR, email)
            );
        }

        Role type = roleService.findByRoleType(RoleType.ROLE_CUSTOMER);
        Set<Role> roles = new HashSet<>();
        roles.add(type);

        String encodedPassword = passwordEncoder.encode(registerPayload.password());

        User user = new User();
        user.setFirstName(registerPayload.firstName());
        user.setLastName(registerPayload.lastName());
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setPhoneNumber(registerPayload.phoneNumber());
        user.setAddress(registerPayload.address());
        user.setZipCode(registerPayload.zipCode());
        user.setRoles(roles);

        userRepository.save(user);

        return new CarRentalResponse(CarrentalResponseMessage.REGISTER_RESPONSE, true);
    }
}
