package com.skinora.skinorabackend.controller;

import com.skinora.skinorabackend.entity.Appointment;
import com.skinora.skinorabackend.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    // Отримати всі записи пацієнта
    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatient(@PathVariable Integer patientId) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }

    // Отримати всі записи лікаря
    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsByDoctor(@PathVariable Integer doctorId) {
        return appointmentService.getAppointmentsByDoctor(doctorId);
    }

    // Створити новий запис
    @PostMapping
    public void createAppointment(@RequestBody Appointment appointment) {
        appointmentService.createAppointment(appointment);
    }

    // Скасувати запис
    @PatchMapping("/{appointmentId}/cancel")
    public void cancelAppointment(@PathVariable Integer appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
    }

    // Підтвердити запис
    @PatchMapping("/{appointmentId}/confirm")
    public void confirmAppointment(@PathVariable Integer appointmentId) {
        appointmentService.confirmAppointment(appointmentId);
    }
}
