package com.skinora.skinorabackend.repository;

import com.skinora.skinorabackend.entity.Appointment;
import com.skinora.skinorabackend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByPatientId(Integer patientId);
    List<Appointment> findByDoctorId(Integer doctorId);
    List<Appointment> findByDoctorIdAndDateBetween(Integer doctorId, LocalDate from, LocalDate to);
    Optional<Appointment> findByIdAndPatientId(Integer id, Integer patientId);

}
