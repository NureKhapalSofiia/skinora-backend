package com.skinora.skinorabackend.service;

import com.skinora.skinorabackend.entity.Role;
import com.skinora.skinorabackend.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Отримати роль за ім'ям
    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found with name: " + roleName));
    }
}
