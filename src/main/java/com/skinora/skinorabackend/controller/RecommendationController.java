package com.skinora.skinorabackend.controller;

import com.skinora.skinorabackend.entity.Recommendation;
import com.skinora.skinorabackend.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    // Отримати всі рекомендації для пацієнта
    @GetMapping("/patient/{patientId}")
    public List<Recommendation> getRecommendationsByPatient(@PathVariable Integer patientId) {
        return recommendationService.getRecommendationsByPatient(patientId);
    }

    // Отримати всі рекомендації, які дав лікар
    @GetMapping("/doctor/{doctorId}")
    public List<Recommendation> getRecommendationsByDoctor(@PathVariable Integer doctorId) {
        return recommendationService.getRecommendationsByDoctor(doctorId);
    }

    // Отримати рекомендації для пацієнта по конкретному продукту
    @GetMapping("/patient/{patientId}/product/{productId}")
    public List<Recommendation> getRecommendationsByPatientAndProduct(@PathVariable Integer patientId,
                                                                      @PathVariable Integer productId) {
        return recommendationService.getRecommendationsByPatientAndProduct(patientId, productId);
    }
}
