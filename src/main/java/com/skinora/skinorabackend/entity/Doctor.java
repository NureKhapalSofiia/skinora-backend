package com.skinora.skinorabackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="doctors")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String specialization;

    @Column(name="expirience_years", nullable = false)
    private int expirienceYears;

    @Column(nullable = false)
    private String bio;

    //За кожним лікарем закріплений ІОТ-девайс
    @JoinColumn(nullable = false, unique = true, name="iot_device_id")
    @OneToOne(fetch = FetchType.EAGER)
    private Device iotNumber;

}
