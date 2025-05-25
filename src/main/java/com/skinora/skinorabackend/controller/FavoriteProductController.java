package com.skinora.skinorabackend.controller;

import com.skinora.skinorabackend.entity.FavoriteProduct;
import com.skinora.skinorabackend.service.FavoriteProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteProductController {

    private final FavoriteProductService favoriteProductService;

    // Додати продукт у улюблені
    @PostMapping("/add")
    public FavoriteProduct addFavorite(@RequestParam Integer patientId, @RequestParam Integer productId) {
        return favoriteProductService.addFavoriteProduct(patientId, productId);
    }

    // Видалити продукт із улюблених
    @DeleteMapping("/remove")
    public void removeFavorite(@RequestParam Integer patientId, @RequestParam Integer productId) {
        favoriteProductService.removeFavoriteProduct(patientId, productId);
    }

    // Отримати всі улюблені продукти пацієнта
    @GetMapping("/patient/{patientId}")
    public List<FavoriteProduct> getFavoritesByPatient(@PathVariable Integer patientId) {
        return favoriteProductService.getFavoritesByPatient(patientId);
    }
}
