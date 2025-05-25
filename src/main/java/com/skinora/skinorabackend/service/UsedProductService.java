package com.skinora.skinorabackend.service;

import com.skinora.skinorabackend.entity.Patient;
import com.skinora.skinorabackend.entity.Product;
import com.skinora.skinorabackend.entity.UsedProduct;
import com.skinora.skinorabackend.repository.PatientRepository;
import com.skinora.skinorabackend.repository.ProductRepository;
import com.skinora.skinorabackend.repository.UsedProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsedProductService {

    private final UsedProductRepository usedProductRepository;
    private final ProductRepository productRepository;
    private final PatientRepository patientRepository;

    public UsedProductService(UsedProductRepository usedProductRepository, ProductRepository productRepository, PatientRepository patientRepository) {
        this.usedProductRepository = usedProductRepository;
        this.productRepository = productRepository;
        this.patientRepository = patientRepository;
    }

    // Додати продукт у used_products і список "Використовуються"
    public void addProductToUsed(Integer patientId, Integer productId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found: " + patientId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

        UsedProduct usedProduct = UsedProduct.builder()
                .patient(patient)       // здесь нужен об'єкт Patient!
                .product(product)       // здесь об'єкт Product
                .startedAt(LocalDate.now())
                .build();

        usedProductRepository.save(usedProduct);

    }

    //Отримати всі використані продукти пацієнта
    public List<UsedProduct> getUsedProductsByPatient(Integer patientId) {
        return usedProductRepository.findByPatientId(patientId);
    }

    //Отримати конкретний запис використаного продукту
    public Optional<UsedProduct> getUsedProductRecord(Integer patientId, Integer productId) {
        return usedProductRepository.findByPatientIdAndProductId(patientId, productId);
    }

}