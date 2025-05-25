package com.skinora.skinorabackend.controller;

import com.skinora.skinorabackend.entity.*;
import com.skinora.skinorabackend.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Integer id){
        return patientService.getPatientById(id);
    }

    @GetMapping
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}/measurement")
    public List<Measurement> getSkinMeasurement(@PathVariable Integer id){
        return patientService.getSkinMeasurements(id);
    }

    @GetMapping("/{id}/recommendations")
    public List<Recommendation> getPersonalizedRecommendations(@PathVariable Integer id){
        return patientService.getPersonalizedRecommendations(id);
    }

    @GetMapping("/{id}/used-products")
    public List<UsedProduct> getUsedProducts(@PathVariable Integer id){
        return patientService.getUsedProducts(id);
    }

    @PostMapping("/{id}/used-products")
    public void addUsedProduct(@PathVariable Integer id, @RequestBody UsedProduct usedProduct){
        patientService.addProductToUsed(id, usedProduct.getId());
    }
}
