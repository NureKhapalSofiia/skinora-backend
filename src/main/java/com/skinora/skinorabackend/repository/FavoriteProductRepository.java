package com.skinora.skinorabackend.repository;

import com.skinora.skinorabackend.entity.FavoriteProduct;
import com.skinora.skinorabackend.entity.Patient;
import com.skinora.skinorabackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {
    List<FavoriteProduct> findByPatient(Patient patient);
    Optional<FavoriteProduct> findByPatientAndProduct(Patient patient, Product product);
}
