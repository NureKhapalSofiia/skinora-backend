package com.skinora.skinorabackend.repository;

import com.skinora.skinorabackend.entity.Patient;
import com.skinora.skinorabackend.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {

    List<Recommendation> findByPatientId(Integer patientId);
    List<Recommendation> findByDoctorId(Integer doctorId);
    List<Recommendation> findByPatientIdAndProductId(Integer patientId, Integer productId);

}
