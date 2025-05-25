package com.skinora.skinorabackend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "using_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsingProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "stopped_at")
    private LocalDateTime stoppedAt;
}
