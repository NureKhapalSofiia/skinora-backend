package com.skinora.skinorabackend.service;

import com.skinora.skinorabackend.entity.Measurement;
import com.skinora.skinorabackend.repository.MeasurementRepository;
import com.skinora.skinorabackend.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final PatientRepository patientRepository;

    public MeasurementService(MeasurementRepository measurementRepository, PatientRepository patientRepository) {
        this.measurementRepository = measurementRepository;
        this.patientRepository = patientRepository;
    }

    //Отримати усі вимірювання пацієнта
    public List<Measurement> getMeasurementsByPatientOrderedByDate(Integer patientId) {
        return measurementRepository.findByPatientIdOrderByDateAsc(patientId);
    }

    //Отримати деталі одного конкретного вимірювання
    public Measurement getMeasurementById(Integer measurementId) {
        return measurementRepository.findById(measurementId)
                .orElseThrow(() -> new RuntimeException("Measurement not found with id: " + measurementId));
    }

}
