package com.skinora.skinorabackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name="datetime", nullable = false)
    private LocalDateTime datetime;

    @Column(nullable = false)
    private String notes;

    @Column(nullable = false)
    private String status; // наприклад: "PENDING", "CONFIRMED", "CANCELLED"
}
