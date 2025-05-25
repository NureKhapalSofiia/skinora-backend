package com.skinora.skinorabackend.service;

import com.skinora.skinorabackend.entity.*;
import com.skinora.skinorabackend.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final ProductRepository productRepository;
    private final UsedProductRepository usedProductRepository;
    private final RecommendationRepository recommendationRepository;
    private final FeedbackRepository feedbackRepository;
    private final DeviceRepository deviceRepository;
    private final MeasurementRepository measurementRepository;

    public DoctorService(DoctorRepository doctorRepository, PatientRepository patientRepository, AppointmentRepository appointmentRepository, ProductRepository productRepository, UsedProductRepository usedProductRepository, RecommendationRepository recommendationRepository, FeedbackRepository feedbackRepository, DeviceRepository deviceRepository, MeasurementRepository measurementRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.productRepository = productRepository;
        this.usedProductRepository = usedProductRepository;
        this.recommendationRepository = recommendationRepository;
        this.feedbackRepository = feedbackRepository;
        this.deviceRepository = deviceRepository;
        this.measurementRepository = measurementRepository;
    }


    //Отримати пацієнтів лікаря
    public List<Patient> getPatientsOfDoctor(Integer doctorId) {
        return patientRepository.findByDoctorId(doctorId);
    }

    //Отримати пацієнта лікаря
    public Patient getPatient(Integer doctorId, Integer patientId) {
        return patientRepository.findByDoctorIdAndId(doctorId, patientId)
                .orElseThrow(() -> new RuntimeException("Patient with id " + patientId + " not found for Doctor with id " + doctorId));
    }

    //Отримати записи до лікаря
    public List<Appointment> getAppointments(Integer doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    //Призначити продукт пацієнту
    public void assignProductToPatient(Integer doctorId, Integer patientId, Integer productId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        UsedProduct usedProduct = UsedProduct.builder()
                .patient(patient)
                .product(product)
                .startedAt(LocalDate.now())
                .build();

        usedProductRepository.save(usedProduct);
    }

    //Створити рекомендацію для пацієнта
    public List<Recommendation> createRecommendation(Integer doctorId, Integer patientId, String Text) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));

        Recommendation recommendation = new Recommendation();
        recommendation.setPatient(patient);
        recommendation.setDoctor(doctor);
        recommendation.setText(Text);
        recommendation.setCreatedAt(LocalDateTime.now());

        recommendationRepository.save(recommendation);

        return recommendationRepository.findByPatientId(patientId);
    }

    //Отримати усі відгуки про лікаря
    public List<Feedback> getFeedbacks(Integer doctorId) {
        return feedbackRepository.findByDoctorId(doctorId);
    }

    //Додати аналіз пацієнту
    public void addMeasurement(String patientName, Integer deviceId, String skinType, String moistureLevel, Double temperature) {
        Patient patient = patientRepository.findByName(patientName)
                .orElseThrow(() -> new RuntimeException("Patient not found with name: " + patientName));

        Device device = deviceRepository.findDeviceById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found with id: " + deviceId));

        Measurement measurement = new Measurement();
        measurement.setPatient(patient);
        measurement.setDevice(device);
        measurement.setSkinType(skinType);
        measurement.setMoistureLevel(Double.valueOf(moistureLevel));
        measurement.setTemperature(temperature);
        measurement.setTakenAt(LocalDateTime.now());

        measurementRepository.save(measurement);
    }

}
