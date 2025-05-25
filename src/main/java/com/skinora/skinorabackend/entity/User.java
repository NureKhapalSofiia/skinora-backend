package com.skinora.skinorabackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.apache.bcel.classfile.LocalVariable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_Hash", nullable = false)
    private String passwordHash;

    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @Column (name = "is_active")
    private boolean isActive;

    @Column(name="created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name="updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
