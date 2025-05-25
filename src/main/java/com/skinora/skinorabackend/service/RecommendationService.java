package com.skinora.skinorabackend.service;

import com.skinora.skinorabackend.entity.Recommendation;
import com.skinora.skinorabackend.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    private final UsedProductRepository usedProductRepository;
    private final DoctorRepository doctorRepository;
    private final ProductRepository productRepository;
    private final PatientRepository patientRepository;
    private final RecommendationRepository recommendationRepository;

    public RecommendationService(UsedProductRepository usedProductRepository, DoctorRepository doctorRepository, ProductRepository productRepository, PatientRepository patientRepository, RecommendationRepository recommendationRepository) {
        this.usedProductRepository = usedProductRepository;
        this.doctorRepository = doctorRepository;
        this.productRepository = productRepository;
        this.patientRepository = patientRepository;
        this.recommendationRepository = recommendationRepository;
    }


    //Отримати всі рекомендації для пацієнта
    public List<Recommendation> getRecommendationsByPatient(Integer patientId) {
        return recommendationRepository.findByPatientId(patientId);
    }

    //Отримати всі рекомендації, які дав лікар
    public List<Recommendation> getRecommendationsByDoctor(Integer doctorId) {
        return recommendationRepository.findByDoctorId(doctorId);
    }

    //Отримати рекомендації для пацієнта по конкретному продукту
    public List<Recommendation> getRecommendationsByPatientAndProduct(Integer patientId, Integer productId) {
        return recommendationRepository.findByPatientIdAndProductId(patientId, productId);
    }

}
