package com.skinora.skinorabackend.repository;

import com.skinora.skinorabackend.entity.Measurement;
import com.skinora.skinorabackend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    List<Measurement> findByPatientIdOrderByDateAsc(Integer patientId);
    List<Measurement> findByPatientIdAndType(Integer patientId, String type);
    Optional<Measurement> findById(Integer measurementId);
}
