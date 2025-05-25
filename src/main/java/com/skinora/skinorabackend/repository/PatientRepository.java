package com.skinora.skinorabackend.repository;

import com.skinora.skinorabackend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Optional<Patient> findByEmail(String email);
    List<Patient> findByDoctorId(Integer DoctorId);
    Optional<Patient> findByName(String Name);
    Optional<Patient> FindByUserId(Integer userId);
    Optional<Patient> findByDoctorIdAndId(Integer doctorId, Integer id);
}
