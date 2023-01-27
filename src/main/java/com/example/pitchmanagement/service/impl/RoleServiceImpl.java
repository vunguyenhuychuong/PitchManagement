package com.example.pitchmanagement.service.impl;

import com.example.pitchmanagement.entity.Role;
import com.example.pitchmanagement.repository.RoleRepository;
import com.example.pitchmanagement.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findRoleById(String roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }
}
