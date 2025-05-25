package com.skinora.skinorabackend.repository;

import com.skinora.skinorabackend.entity.Doctor;
import com.skinora.skinorabackend.entity.DoctorPatient;
import com.skinora.skinorabackend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorPatientRepository extends JpaRepository<DoctorPatient, Integer> {

    List<DoctorPatient> findByDoctorId(Doctor doctor);
    List<DoctorPatient> findByPatientId(Patient patient);
    Optional<DoctorPatient> findByDoctorIdAndPatientId(Doctor doctor, Patient patient);

}
