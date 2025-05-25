package com.skinora.skinorabackend.service;

import com.skinora.skinorabackend.entity.Role;
import com.skinora.skinorabackend.entity.User;
import com.skinora.skinorabackend.repository.RoleRepository;
import com.skinora.skinorabackend.repository.UserRepository;
import com.skinora.skinorabackend.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    //Реєстрація
    public User registerUser(String fullName, String email, String password, String roleName) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User with email " + email + " already exists");
        }

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role " + roleName + " not found"));
        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setEmail(email);
        newUser.setPasswordHash(passwordEncoder.encode(password));
        newUser.setRole(role);
        newUser.setAvatarUrl("");
        newUser.setActive(true);

        return userRepository.save(newUser);
    }

    //Отримання ролі за Email
    public String getUserRole(String email) {
        User user = userRepository.findByEmail(email).
                orElseThrow(() -> new RuntimeException("User " + email + " not found"));

        return user.getRole().getName();
    }

    //Аутентифікація користувача
    public String authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User " + email + " not found"));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Wrong password");
        }

        return jwtService.generateToken(user.getEmail());

    }

}

