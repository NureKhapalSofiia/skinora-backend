package com.skinora.skinorabackend.service;

import com.skinora.skinorabackend.entity.User;
import com.skinora.skinorabackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Пошук користувача за id
    public User getUserById (Integer id){

        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User " + id + " not found"));
    }

    //Оновлення інформації про користувача
    public User updateUserProfile(User updatedUser) {
        User existingUser = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new RuntimeException("User " + updatedUser.getId() + " not found"));

        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPasswordHash(updatedUser.getPasswordHash());

        return userRepository.save(existingUser);
    }

    //Видалення користувача
    public void deleteUserById(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User " + id + " not found");
        }

        userRepository.deleteById(id);
    }

    //Зміна Password
    public User changePassword(Integer id, String oldPassword, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Користувач з ID " + id + " не знайдено"));

        if (!passwordEncoder.matches(oldPassword, user.getPasswordHash())) {
            throw new RuntimeException("Старий пароль невірний");
        }

        if (passwordEncoder.matches(newPassword, user.getPasswordHash())) {
            throw new RuntimeException("Новий пароль не може бути таким самим як старий");
        }

        user.setPasswordHash(passwordEncoder.encode(newPassword));

        return userRepository.save(user);
    }

}
