package com.skinora.skinorabackend.service;

import com.skinora.skinorabackend.entity.FavoriteProduct;
import com.skinora.skinorabackend.entity.Patient;
import com.skinora.skinorabackend.entity.Product;
import com.skinora.skinorabackend.repository.FavoriteProductRepository;
import com.skinora.skinorabackend.repository.PatientRepository;
import com.skinora.skinorabackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteProductService {

    private final FavoriteProductRepository favoriteProductRepository;
    private final PatientRepository patientRepository;
    private final ProductRepository productRepository;

    // Додати продукт у улюблені
    public FavoriteProduct addFavoriteProduct(Integer patientId, Integer productId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        FavoriteProduct favoriteProduct = FavoriteProduct.builder()
                .patient(patient)
                .product(product)
                .addedAt(LocalDateTime.now())
                .build();

        return favoriteProductRepository.save(favoriteProduct);
    }

    // Видалити продукт із улюблених
    public void removeFavoriteProduct(Integer patientId, Integer productId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        FavoriteProduct favoriteProduct = favoriteProductRepository.findByPatientAndProduct(patient, product)
                .orElseThrow(() -> new RuntimeException("Favorite product not found"));

        favoriteProductRepository.delete(favoriteProduct);
    }

    // Отримати всі улюблені продукти пацієнта
    public List<FavoriteProduct> getFavoritesByPatient(Integer patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return favoriteProductRepository.findByPatient(patient);
    }
}
