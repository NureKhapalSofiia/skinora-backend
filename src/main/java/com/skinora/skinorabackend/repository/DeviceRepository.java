package com.skinora.skinorabackend.repository;

import com.skinora.skinorabackend.entity.Device;
import com.skinora.skinorabackend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Patient, Integer> {

    List<Device> findByPatientId(Integer patientId);
    Optional<Device> findBySerialNumber(String serialNumber);
    Optional<Device> findDeviceById(Integer id);
}
