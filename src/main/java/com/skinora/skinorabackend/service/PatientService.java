package com.skinora.skinorabackend.service;

import com.skinora.skinorabackend.entity.*;
import com.skinora.skinorabackend.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final MeasurementRepository measurementRepository;
    private final RecommendationRepository recommendationRepository;
    private final UsedProductRepository usedProductRepository;
    private final ProductRepository productRepository;


    public PatientService(PatientRepository patientRepository, MeasurementRepository measurementRepository, RecommendationRepository recommendationRepository, UsedProductRepository usedProductRepository, ProductRepository productRepository) {
        this.patientRepository = patientRepository;
        this.measurementRepository = measurementRepository;
        this.recommendationRepository = recommendationRepository;
        this.usedProductRepository = usedProductRepository;
        this.productRepository = productRepository;
    }

    //Пошук всіх пацієнтів
    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    //Пошук пацієнта за ід
    public Patient getPatientById(Integer id){
        return patientRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Patient " + id + " not found"));
    }

    //Отримати зміну шкіри
    public List<Measurement> getSkinMeasurements(Integer patientId){
        return measurementRepository.findByPatientIdOrderByDateAsc(patientId);
    }

    //Персональні рекомендації
    public List<Recommendation> getPersonalizedRecommendations(Integer patientId) {
        return recommendationRepository.findByPatientId(patientId);
    }

    //Використані продукти
    public List<UsedProduct> getUsedProducts(Integer patientId) {
        return usedProductRepository.findByPatientId(patientId);
    }

    //Додати продукт до використаних
    public void addProductToUsed(Integer patientId, Integer productId) {
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



}
