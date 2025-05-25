package com.skinora.skinorabackend.controller;

import com.skinora.skinorabackend.entity.Role;
import com.skinora.skinorabackend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    // Отримати роль за ім'ям
    @GetMapping("/{roleName}")
    public Role getRoleByName(@PathVariable String roleName) {
        return roleService.getRoleByName(roleName);
    }
}
