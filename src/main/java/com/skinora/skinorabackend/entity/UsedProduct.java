package com.skinora.skinorabackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="used_products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public enum ProductUsageStatus {
        ACTIVE,
        COMPLETED,
        INTERRUPTED
    }
    // Наприклад: "використовується", "завершено", "перервано"

    @Column(name = "started_at")
    private LocalDate startedAt;

    @Column(name = "stopped_at")
    private LocalDate stoppedAt;

}
