package com.skinora.skinorabackend.controller;

import com.skinora.skinorabackend.entity.Product;
import com.skinora.skinorabackend.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Пошук продуктів за назвою
    @GetMapping("/find")
    public List<Product> searchProductsByName(String keyword) {
        return productService.searchProductsByName(keyword);
    }

    //Фільтрація продуктів за категорією
    @GetMapping("filter")
    public List<Product> filterProductsByCategory(String category) {
        return productService.filterProductsByCategory(category);
    }

    // Сортування продуктів за категорією (алфавітно)
    @GetMapping("/sort/category")
    public List<Product> sortProductsByCategory() {
        return productService.sortProductsByCategory();
    }

    // Сортування продуктів за брендом (алфавітно)
    @GetMapping("/sort/brand")
    public List<Product> sortProductsByBrand() {
        return productService.sortProductsByBrand();
    }

    //Отримати продукти за категорією
    @GetMapping("/find/category")
    public List<Product> getProductsByCategory(String category) {
        return productService.getProductsByCategory(category);
    }

    //Отримати продукт за ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

}
