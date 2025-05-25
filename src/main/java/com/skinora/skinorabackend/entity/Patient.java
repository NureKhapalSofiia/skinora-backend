package com.skinora.skinorabackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name="patients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column (name="date_of_birthday",nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String gender;

    //Нотатки до пацієнта(алергія, хвороби, особливості)
    @Column(nullable = false)
    private String notes;
}
