package com.skinora.skinorabackend.controller;

import com.skinora.skinorabackend.entity.PrescribedProduct;
import com.skinora.skinorabackend.service.PrescribedProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prescribed-products")
@RequiredArgsConstructor
public class PrescribedProductController {

    private final PrescribedProductService prescribedProductService;

    // Додати призначений продукт пацієнту
    @PostMapping("/add")
    public void prescribeProduct(@RequestParam Integer doctorId,
                                 @RequestParam Integer patientId,
                                 @RequestParam Integer productId,
                                 @RequestParam(required = false) String notes) {
        prescribedProductService.prescribeProduct(doctorId, patientId, productId, notes);
    }

    // Отримати всі призначені продукти пацієнта
    @GetMapping("/patient/{patientId}")
    public List<PrescribedProduct> getPrescribedProductsByPatient(@PathVariable Integer patientId) {
        return prescribedProductService.getPrescribedProductsByPatient(patientId);
    }

    // Отримати конкретний призначений продукт пацієнта
    @GetMapping("/patient/{patientId}/product/{productId}")
    public Optional<PrescribedProduct> getPrescribedProductRecord(@PathVariable Integer patientId,
                                                                  @PathVariable Integer productId) {
        return prescribedProductService.getPrescribedProductRecord(patientId, productId);
    }
}
