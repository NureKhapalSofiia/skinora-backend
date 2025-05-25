package com.skinora.skinorabackend.service;

import com.skinora.skinorabackend.entity.Product;
import com.skinora.skinorabackend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Пошук продуктів за назвою
    public List<Product> searchProductsByName(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    //Фільтрація продуктів за категорією
    public List<Product> filterProductsByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    // Сортування продуктів за категорією (алфавітно)
    public List<Product> sortProductsByCategory() {
        return productRepository.findAllByOrderByCategoryAsc();
    }

    // Сортування продуктів за брендом (алфавітно)
    public List<Product> sortProductsByBrand() {
        return productRepository.findAllByOrderByBrandAsc();
    }

    //Отримати продукти за категорією
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByNameContainingIgnoreCase(category);
    }

    //Отримати продукт за ID
    public Product getProductById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
    }



}
