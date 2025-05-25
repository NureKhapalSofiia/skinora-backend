package com.skinora.skinorabackend.service;

import com.skinora.skinorabackend.entity.Appointment;
import com.skinora.skinorabackend.entity.Patient;
import com.skinora.skinorabackend.entity.Doctor;
import com.skinora.skinorabackend.repository.AppointmentRepository;
import com.skinora.skinorabackend.repository.PatientRepository;
import com.skinora.skinorabackend.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository,
                              DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    //Отримати всі записи пацієнта
    public List<Appointment> getAppointmentsByPatient(Integer patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));
        return appointmentRepository.findByPatientId(patientId);
    }

    //Отримати всі записи лікаря
    public List<Appointment> getAppointmentsByDoctor(Integer doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));
        return appointmentRepository.findByDoctorId(doctorId);
    }

    //Створити запис
    public void createAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    //Скасувати запис
    public void cancelAppointment(Integer appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + appointmentId));
        appointment.setStatus("CANCELLED");
        appointmentRepository.save(appointment);
    }

    //Підтвердити запис
    public void confirmAppointment(Integer appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + appointmentId));
        appointment.setStatus("CONFIRMED");
        appointmentRepository.save(appointment);
    }
}
