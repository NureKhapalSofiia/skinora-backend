package com.skinora.skinorabackend.service;

import com.skinora.skinorabackend.entity.Doctor;
import com.skinora.skinorabackend.entity.Patient;
import com.skinora.skinorabackend.entity.PrescribedProduct;
import com.skinora.skinorabackend.entity.Product;
import com.skinora.skinorabackend.repository.DoctorRepository;
import com.skinora.skinorabackend.repository.PatientRepository;
import com.skinora.skinorabackend.repository.PrescribedProductRepository;
import com.skinora.skinorabackend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PrescribedProductService {

    private final PatientRepository patientRepository;
    private final PrescribedProductRepository prescribedProductRepository;
    private final ProductRepository productRepository;
    private final DoctorRepository doctorRepository;

    public PrescribedProductService(PatientRepository patientRepository, PrescribedProductRepository prescribedProductRepository, ProductRepository productRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.prescribedProductRepository = prescribedProductRepository;
        this.productRepository = productRepository;
        this.doctorRepository = doctorRepository;
    }

    //Додати
    public void prescribeProduct(Integer doctorId, Integer patientId, Integer productId, String notes) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found: " + doctorId));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found: " + patientId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

        PrescribedProduct prescribedProduct = PrescribedProduct.builder()
                .doctor(doctor)         // объект Doctor
                .patient(patient)       // объект Patient
                .product(product)       // объект Product
                .startDate(LocalDate.now())
                .notes(notes)
                .build();

        prescribedProductRepository.save(prescribedProduct);
    }

    //Отримати призначені продукти пацієнта
    public List<PrescribedProduct> getPrescribedProductsByPatient(Integer patientId) {
        return prescribedProductRepository.findByPatientId(patientId);
    }

    //Отримати призначений продукт пацієнта
    public Optional<PrescribedProduct> getPrescribedProductRecord(Integer patientId, Integer productId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        return prescribedProductRepository.findByPatientAndProduct(patient, product);
    }

}
