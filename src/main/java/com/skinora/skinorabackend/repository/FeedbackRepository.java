package com.skinora.skinorabackend.repository;

import com.skinora.skinorabackend.entity.Feedback;
import com.skinora.skinorabackend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    List<Feedback> findByDoctorId(Integer doctorId);
    List<Feedback> findByPatientId(Integer patientId);
    Double countByDoctorId(Integer doctorId);

}
