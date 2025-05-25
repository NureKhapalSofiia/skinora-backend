package com.skinora.skinorabackend.controller;

import com.skinora.skinorabackend.entity.Feedback;
import com.skinora.skinorabackend.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    // Отримати всі відгуки для лікаря
    @GetMapping("/doctor/{doctorId}")
    public List<Feedback> getFeedbacksByDoctor(@PathVariable Integer doctorId) {
        return feedbackService.getFeedbacksByDoctor(doctorId);
    }

    // Отримати всі відгуки від пацієнта
    @GetMapping("/patient/{patientId}")
    public List<Feedback> getFeedbacksByPatient(@PathVariable Integer patientId) {
        return feedbackService.getFeedbacksByPatient(patientId);
    }

    // Отримати середній рейтинг лікаря
    @GetMapping("/doctor/{doctorId}/rating")
    public Double getDoctorRating(@PathVariable Integer doctorId) {
        return feedbackService.getDoctorRating(doctorId);
    }

    // Отримати деталі конкретного відгуку
    @GetMapping("/{feedbackId}")
    public Feedback getFeedbackById(@PathVariable Integer feedbackId) {
        return feedbackService.getFeedbackById(feedbackId);
    }
}
