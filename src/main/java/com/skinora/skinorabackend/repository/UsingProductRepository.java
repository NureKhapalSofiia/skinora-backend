package com.skinora.skinorabackend.repository;

import com.skinora.skinorabackend.entity.UsingProduct;
import com.skinora.skinorabackend.entity.Patient;
import com.skinora.skinorabackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsingProductRepository extends JpaRepository<UsingProduct, Long> {
    List<UsingProduct> findByPatient(Patient patient);
    Optional<UsingProduct> findByPatientAndProduct(Patient patient, Product product);
}
