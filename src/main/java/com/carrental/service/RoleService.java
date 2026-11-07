package com.carrental.service;

import com.carrental.domain.Role;
import com.carrental.domain.enums.RoleType;
import com.carrental.exception.ResourceNotFoundException;
import com.carrental.exception.message.ErrorMessage;
import com.carrental.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByRoleType(RoleType roleType) {
        return roleRepository.findByRoleType(roleType).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND, roleType.name()))
        );
    }
}
