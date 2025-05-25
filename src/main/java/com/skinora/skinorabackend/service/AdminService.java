package com.skinora.skinorabackend.service;

import com.skinora.skinorabackend.entity.*;
import com.skinora.skinorabackend.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorPatientRepository doctorPatientRepository;

    public AdminService(DoctorRepository doctorRepository, PatientRepository patientRepository, ProductRepository productRepository, UserRepository userRepository, AppointmentRepository appointmentRepository, DoctorPatientRepository doctorPatientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.doctorPatientRepository = doctorPatientRepository;
    }

    // Призначити лікаря пацієнту
    public void assignDoctorToPatient(Integer doctorId, Integer patientId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));

        DoctorPatient doctorPatient = DoctorPatient.builder()
                .doctorId(doctor)
                .patientId(patient)
                .assignedAt(LocalDateTime.now())
                .build();

        doctorPatientRepository.save(doctorPatient);
    }

    //Додати продукт
    public void addProduct(String name, String brand, String description, Boolean isCertified, Admin createdBy, String category) {
        Product product = new Product();

        product.setName(name);
        product.setBrand(brand);
        product.setDescription(description);
        product.setCertified(isCertified);
        product.setCreatedBy(createdBy);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        product.setCategory(category);

        productRepository.save(product);
    }

    //Oновити
    @Transactional
    public Product updateProduct(Integer productId, Product updatedProductData) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        if (updatedProductData.getName() != null) {
            existingProduct.setName(updatedProductData.getName());
        }
        if (updatedProductData.getBrand() != null) {
            existingProduct.setBrand(updatedProductData.getBrand());
        }
        if (updatedProductData.getDescription() != null) {
            existingProduct.setDescription(updatedProductData.getDescription());
        }
        if (updatedProductData.getCategory() != null) {
            existingProduct.setCategory(updatedProductData.getCategory());
        }
        if (updatedProductData.getCreatedBy() != null) {
            existingProduct.setCreatedBy(updatedProductData.getCreatedBy());
        }

        existingProduct.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(existingProduct);
    }

    //Видалити продукт
    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }

    //Отримати всіх користувачів
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Отримати загальну статистику системи
    public Map<String, Object> getSystemStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userRepository.count());
        stats.put("totalDoctors", doctorRepository.count());
        stats.put("totalPatients", patientRepository.count());
        stats.put("totalProducts", productRepository.count());
        stats.put("totalAppointments", appointmentRepository.count());
        return stats;
    }

    //Отримати рейтинг лікарів
    public List<Doctor> getDoctorRatings() {
        return doctorRepository.findAllByOrderByRatingDesc();
    }



}
