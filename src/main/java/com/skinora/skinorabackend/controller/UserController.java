package com.skinora.skinorabackend.controller;

import com.skinora.skinorabackend.entity.User;
import com.skinora.skinorabackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Пошук користувача за ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    // Оновлення профілю користувача
    @PutMapping("/update")
    public User updateUserProfile(@RequestBody User updatedUser) {
        return userService.updateUserProfile(updatedUser);
    }

    // Видалення користувача
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }

    // Зміна паролю користувача
    @PatchMapping("/{id}/change-password")
    public User changePassword(@PathVariable Integer id,
                               @RequestParam String oldPassword,
                               @RequestParam String newPassword) {
        return userService.changePassword(id, oldPassword, newPassword);
    }
}
