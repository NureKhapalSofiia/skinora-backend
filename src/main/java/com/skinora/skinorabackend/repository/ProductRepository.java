package com.skinora.skinorabackend.repository;

import com.skinora.skinorabackend.entity.Patient;
import com.skinora.skinorabackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNameContainingIgnoreCase(String keyword);
    List<Product> findByCategory(String category);
    Optional<Product> findById(Integer id);
    List<Product> findByCategoryIgnoreCase(String category);
    List<Product> findAllByOrderByCategoryAsc();
    List<Product> findAllByOrderByBrandAsc();
}