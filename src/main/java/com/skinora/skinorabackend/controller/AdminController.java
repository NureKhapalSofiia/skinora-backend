package com.skinora.skinorabackend.controller;

import com.skinora.skinorabackend.entity.Admin;
import com.skinora.skinorabackend.entity.Doctor;
import com.skinora.skinorabackend.entity.Product;
import com.skinora.skinorabackend.entity.User;
import com.skinora.skinorabackend.service.AdminService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddPoductRequest{
        private Admin admin;
        private String name;
        private String brand;
        private String description;
        private Boolean isCertified;
        private String category;
    }

    // Призначити лікаря пацієнту
    @PostMapping("/doctor/{doctorId}/patient/{patientId}")
    public void setDoctor(@PathVariable Integer doctorId, @PathVariable Integer patientId) {
        adminService.assignDoctorToPatient(doctorId, patientId);
    }

    //Додати продукт
    @PostMapping("/product")
    public void addProduct(@RequestBody AddPoductRequest request) {
        adminService.addProduct(
        request.getName(),
        request.getBrand(),
        request.getDescription(),
        request.getIsCertified(),
        request.getAdmin(),
        request.getCategory()
        );
    }

    //Редагувати продукт
    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable Integer productId, @RequestBody Product updatedProductData) {
        return adminService.updateProduct(productId, updatedProductData);
    }

    //Видалити продукт
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Integer productId) {
        adminService.deleteProduct(productId);
    }

    //Отримати всіх користувачів
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    //Отримати загальну статистику системи
    @GetMapping("/statistics")
    public Map<String, Object> getSystemStatistics() {
        return adminService.getSystemStatistics();
    }

    //Отримати рейтинг лікарів
    @GetMapping("/doctor-rating")
    public List<Doctor> getDoctorRating(){
        return adminService.getDoctorRatings();
    }

}
