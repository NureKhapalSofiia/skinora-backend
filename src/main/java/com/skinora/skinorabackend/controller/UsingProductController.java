package com.skinora.skinorabackend.controller;

import com.skinora.skinorabackend.entity.UsingProduct;
import com.skinora.skinorabackend.service.UsingProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/using-products")
@RequiredArgsConstructor
public class UsingProductController {

    private final UsingProductService usingProductService;

    // Додати продукт у "Використовуються"
    @PostMapping("/add")
    public UsingProduct addUsingProduct(@RequestParam Integer patientId, @RequestParam Integer productId) {
        return usingProductService.addUsingProduct(patientId, productId);
    }

    // Завершити використання продукту
    @PatchMapping("/stop")
    public UsingProduct stopUsingProduct(@RequestParam Integer patientId, @RequestParam Integer productId) {
        return usingProductService.stopUsingProduct(patientId, productId);
    }

    // Отримати всі продукти, які використовуються пацієнтом
    @GetMapping("/patient/{patientId}")
    public List<UsingProduct> getUsingProductsByPatient(@PathVariable Integer patientId) {
        return usingProductService.getUsingProductsByPatient(patientId);
    }
}
