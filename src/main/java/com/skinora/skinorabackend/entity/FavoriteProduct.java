package com.skinora.skinorabackend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "favorite_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;
}
