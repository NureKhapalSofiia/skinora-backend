package com.skinora.skinorabackend.repository;

import com.skinora.skinorabackend.entity.Patient;
import com.skinora.skinorabackend.entity.PrescribedProduct;
import com.skinora.skinorabackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescribedProductRepository extends JpaRepository<PrescribedProduct, Integer> {

    List<PrescribedProduct> findByPatient(Patient patient);
    List<PrescribedProduct> findByPatientId(Integer patientId);
    Optional<PrescribedProduct> findByPatientAndProduct(Patient patient, Product product);
}
