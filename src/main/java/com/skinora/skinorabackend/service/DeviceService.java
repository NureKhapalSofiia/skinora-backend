package com.skinora.skinorabackend.service;

import com.skinora.skinorabackend.entity.Device;
import com.skinora.skinorabackend.repository.DeviceRepository;
import com.skinora.skinorabackend.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final PatientRepository patientRepository;

    public DeviceService(DeviceRepository deviceRepository, PatientRepository patientRepository) {
        this.deviceRepository = deviceRepository;
        this.patientRepository = patientRepository;
    }

    //Отримати всі пристрої пацієнта
    public List<Device> getDevicesByPatient(Integer patientId) {
        return deviceRepository.findByPatientId(patientId);
    }

    //Отримати пристрій за серійним номером
    public Optional<Device> getDeviceBySerialNumber(String serialNumber) {
        return deviceRepository.findBySerialNumber(serialNumber);
    }
}
