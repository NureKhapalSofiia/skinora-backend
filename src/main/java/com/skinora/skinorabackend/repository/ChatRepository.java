package com.skinora.skinorabackend.repository;

import com.skinora.skinorabackend.entity.Chat;
import com.skinora.skinorabackend.entity.Message;
import com.skinora.skinorabackend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    Optional<Chat> findById(Integer chatId);
    List<Chat> findByPatientId(Integer patientId);
    Optional<Chat> findByPatientIdAndDoctorId(Integer patientId, Integer doctorId);
}
