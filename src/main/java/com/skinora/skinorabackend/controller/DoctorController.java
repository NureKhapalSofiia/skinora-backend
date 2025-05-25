package com.skinora.skinorabackend.controller;

import com.skinora.skinorabackend.entity.*;
import com.skinora.skinorabackend.service.DoctorService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class AddMeasurementRequest {
        private String patientName;
        private Integer deviceId;
        private String skinType;
        private String moistureLevel;
        private LocalDateTime takenAt;
        private Double temperature;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class AssignProductRequest {
        private Integer productId;
        private Integer doctorId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateRecommendationRequest {
        private Integer doctorId;
        private String text;
    }

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Получить всех пациентов врача
    @GetMapping("patients")
    public List<Patient> getPatientsOfDoctor (Integer id){
        return doctorService.getPatientsOfDoctor(id);
    }

    // Получить одного пациента врача
    @GetMapping("/{doctorId}/patients/{patientId}")
    public Patient getPatientById(@PathVariable Integer doctorId, @PathVariable Integer patientId) {
        return doctorService.getPatient(doctorId, patientId);
    }

    // Добавить измерение состояния кожи
    @PostMapping("/patients/measurements")
    public void addMeasurements(@RequestBody AddMeasurementRequest request){
        doctorService.addMeasurement(
                request.getPatientName(),
                request.getDeviceId(),
                request.getSkinType(),
                request.getMoistureLevel(),
                request.getTemperature()
        );
    }

    // Создать рекомендацию для пациента
    @PostMapping("/patients/{id}/recommendations")
    public void addRecommendations( @RequestParam Integer doctorId, @PathVariable Integer patientId, String text) {
        doctorService.createRecommendation(doctorId, patientId, text);
    }

    // Получить все записи к врачу
    @GetMapping("/appointments")
    public List<Appointment> getList (@RequestParam Integer doctorId){
        return doctorService.getAppointments(doctorId);
    }

    // Назначить продукт пациенту
    @PostMapping("/patients/{patientId}/assign-product")


    // Получить все отзывы о враче
    @GetMapping("/feedbacks")
    public List<Feedback> getFeedbacks(@RequestParam Integer doctorId) {
        return doctorService.getFeedbacks(doctorId);
    }
}
