package com.skinora.skinorabackend.controller;

import com.skinora.skinorabackend.entity.UsedProduct;
import com.skinora.skinorabackend.service.UsedProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/used-products")
@RequiredArgsConstructor
public class UsedProductController {

    private final UsedProductService usedProductService;

    // Додати продукт у таблицю used_products
    @PostMapping("/add")
    public void addProductToUsed(
            @RequestParam Integer patientId,
            @RequestParam Integer productId
    ) {
        usedProductService.addProductToUsed(patientId, productId);
    }

    //Отримати всі використані продукти пацієнта
    @GetMapping("/patient/{patientId}")
    public List<UsedProduct> getUsedProductsByPatient(
            @PathVariable Integer patientId
    ) {
        return usedProductService.getUsedProductsByPatient(patientId);
    }

    //Отримати конкретний запис використаного продукту

    @GetMapping("/patient/{patientId}/product/{productId}")
    public Optional<UsedProduct> getUsedProductRecord(
            @PathVariable Integer patientId,
            @PathVariable Integer productId
    ) {
        return usedProductService.getUsedProductRecord(patientId, productId);
    }
}
