package com.skinora.skinorabackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="devices")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="serial_number", nullable = false)
    private int serialNumber;

    @Column(name="registered_at", nullable = false)
    private LocalDateTime registeredAt;
}
