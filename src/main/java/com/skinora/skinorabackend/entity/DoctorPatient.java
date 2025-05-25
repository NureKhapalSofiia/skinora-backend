package com.skinora.skinorabackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="doctor_patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorPatient {

    @Id
    @OneToOne
    @JoinColumn(name="patient_id", nullable = false)
    private Patient patientId;

    @OneToOne
    @JoinColumn(name="doctor_id", nullable = false)
    private Doctor doctorId;

    @Column(name="assigned_at", nullable = false)
    private LocalDateTime assignedAt;
}