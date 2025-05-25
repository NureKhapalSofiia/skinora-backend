package com.skinora.skinorabackend.service;

import com.skinora.skinorabackend.entity.UsingProduct;
import com.skinora.skinorabackend.entity.Patient;
import com.skinora.skinorabackend.entity.Product;
import com.skinora.skinorabackend.repository.UsingProductRepository;
import com.skinora.skinorabackend.repository.PatientRepository;
import com.skinora.skinorabackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsingProductService {

    private final UsingProductRepository usingProductRepository;
    private final PatientRepository patientRepository;
    private final ProductRepository productRepository;

    // Додати продукт у "Використовуються"
    public UsingProduct addUsingProduct(Integer patientId, Integer productId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        UsingProduct usingProduct = UsingProduct.builder()
                .patient(patient)
                .product(product)
                .startedAt(LocalDateTime.now())
                .build();

        return usingProductRepository.save(usingProduct);
    }

    // Завершити використання продукту
    public UsingProduct stopUsingProduct(Integer patientId, Integer productId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        UsingProduct usingProduct = usingProductRepository.findByPatientAndProduct(patient, product)
                .orElseThrow(() -> new RuntimeException("Using product not found"));

        usingProduct.setStoppedAt(LocalDateTime.now());

        return usingProductRepository.save(usingProduct);
    }

    // Отримати всі продукти, які використовуються пацієнтом
    public List<UsingProduct> getUsingProductsByPatient(Integer patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return usingProductRepository.findByPatient(patient);
    }
}
