package com.skinora.skinorabackend.controller;

import com.skinora.skinorabackend.entity.Measurement;
import com.skinora.skinorabackend.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/measurements")
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementService measurementService;

    // Отримати всі вимірювання пацієнта
    @GetMapping("/patient/{patientId}")
    public List<Measurement> getMeasurementsByPatient(@PathVariable Integer patientId) {
        return measurementService.getMeasurementsByPatientOrderedByDate(patientId);
    }

    // Отримати деталі одного вимірювання
    @GetMapping("/{measurementId}")
    public Measurement getMeasurementById(@PathVariable Integer measurementId) {
        return measurementService.getMeasurementById(measurementId);
    }
}
