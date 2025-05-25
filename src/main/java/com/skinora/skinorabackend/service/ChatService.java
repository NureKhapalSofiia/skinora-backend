package com.skinora.skinorabackend.service;

import com.skinora.skinorabackend.entity.*;
import com.skinora.skinorabackend.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public ChatService(ChatRepository chatRepository, MessageRepository messageRepository,
                       PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    //Отримати всі чати пацієнта
    public List<Chat> getChatsByPatient(Integer patientId) {
       return chatRepository.findByPatientId(patientId);
    }

    //Отримати чат пацієнта з конкретним лікарем
    public Chat getChatByPatientAndDoctor(Integer patientId, Integer doctorId) {
        return chatRepository.findByPatientIdAndDoctorId(patientId, doctorId)
                .orElseThrow(() -> new RuntimeException("Chat between patient and doctor not found"));
    }

    //Отримати всі повідомлення чату
    public List<Message> getMessagesByChat(Integer chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat not found with id: " + chatId));
        return messageRepository.findByChatOrderByTimestampAsc(chat);
    }

    //Надіслати повідомлення в чат
    public Message sendMessage(Integer chatId, Message message) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat not found with id: " + chatId));

        message.setChat(chat);
        message.setSentAt(LocalDateTime.now());

        return messageRepository.save(message);
    }
}
