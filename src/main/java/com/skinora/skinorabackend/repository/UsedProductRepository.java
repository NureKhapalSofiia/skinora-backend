package com.skinora.skinorabackend.repository;


import com.skinora.skinorabackend.entity.UsedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsedProductRepository extends JpaRepository<UsedProduct, Integer> {

    List<UsedProduct> findByPatientId(Integer patientId);
    Optional<UsedProduct> findByPatientIdAndProductId(Integer patientId, Integer productId);

}
