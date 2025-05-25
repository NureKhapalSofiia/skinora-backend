package com.skinora.skinorabackend.controller;

import com.skinora.skinorabackend.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegisterRequest {
        private String name;
        private String email;
        private String password;
        private String RoleName;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LogInRequest {
        private String email;
        private String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenResponse {
        private String token;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResponse {
        private int id;
        private String email;
        private String role;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoleResponse {
        private String role;
    }

    private final AuthService authService;

    // Регистрация пользователя
    @PostMapping("/register")
    public UserResponse register (@RequestBody RegisterRequest request){
        var user = authService.registerUser(request.getName(), request.getEmail(), request.getPassword(), request.getRoleName());
        return new UserResponse(user.getId(), user.getEmail(), user.getRole().getName());
    }
    // Аутентификация пользователя
    @PostMapping("/login")
    public TokenResponse login (@RequestBody RegisterRequest request){
        String token = authService.authenticateUser(request.getEmail(), request.getPassword());
        return new TokenResponse(token);
    }

    // Получить роль пользователя по email
    @GetMapping("/role")
    public RoleResponse getRole(@RequestParam String email){
        String role = authService.getUserRole(email);
        return new RoleResponse(role);

    }
}
