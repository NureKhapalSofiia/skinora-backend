package com.skinora.skinorabackend.service;

import com.skinora.skinorabackend.entity.Doctor;
import com.skinora.skinorabackend.entity.Patient;
import com.skinora.skinorabackend.repository.DoctorRepository;
import com.skinora.skinorabackend.repository.FeedbackRepository;
import com.skinora.skinorabackend.repository.PatientRepository;
import org.springframework.stereotype.Service;
import com.skinora.skinorabackend.entity.Feedback;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public FeedbackService(FeedbackRepository feedbackRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.feedbackRepository = feedbackRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }


    //Отримати всі відгуки для лікаря
    public List<Feedback> getFeedbacksByDoctor(Integer doctorId) {
        return feedbackRepository.findByDoctorId(doctorId);
    }

    //Отримати всі відгуки від пацієнта
    public List<Feedback> getFeedbacksByPatient(Integer patientId) {
        return feedbackRepository.findByPatientId(patientId);
    }

    //Порахувати середню оцінку лікаря
    public Double getDoctorRating(Integer doctorId) {
        List<Feedback> feedbacks = feedbackRepository.findByDoctorId(doctorId);

        if (feedbacks.isEmpty()) {
            return 0.0; // Немає відгуків — рейтинг 0
        }

        double average = feedbacks.stream()
                .mapToInt(Feedback::getRating) // getRating() повертає int або double
                .average()
                .orElse(0.0);

        return average;
    }

    //Отримати деталі конкретного відгуку
    public Feedback getFeedbackById(Integer feedbackId) {
        return feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback not found with id: " + feedbackId));
    }

}
