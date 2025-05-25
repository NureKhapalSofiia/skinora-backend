package com.skinora.skinorabackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="oit_measurements")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Measurement {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="patient_id", nullable = false)
    private Patient patient;

    @Column(name="skin_type", nullable = false)
    private String skinType;

    @Column(name="moisture_level", nullable = false)
    private Double moistureLevel;

    @Column(nullable = false)
    private Double temperature;

    @Column(name="taken_at", nullable = false)
    private LocalDateTime takenAt;

    @ManyToOne
    @JoinColumn(name="source_device_id", nullable = false)
    private Device Device;
}
