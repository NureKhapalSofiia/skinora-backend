package com.skinora.skinorabackend.controller;

import com.skinora.skinorabackend.entity.Chat;
import com.skinora.skinorabackend.entity.Message;
import com.skinora.skinorabackend.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    // Отримати всі чати пацієнта
    @GetMapping("/patient/{patientId}")
    public List<Chat> getChatsByPatient(@PathVariable Integer patientId) {
        return chatService.getChatsByPatient(patientId);
    }

    // Отримати чат пацієнта з конкретним лікарем
    @GetMapping("/patient/{patientId}/doctor/{doctorId}")
    public Chat getChatByPatientAndDoctor(@PathVariable Integer patientId,
                                          @PathVariable Integer doctorId) {
        return chatService.getChatByPatientAndDoctor(patientId, doctorId);
    }

    // Отримати всі повідомлення чату
    @GetMapping("/{chatId}/messages")
    public List<Message> getMessagesByChat(@PathVariable Integer chatId) {
        return chatService.getMessagesByChat(chatId);
    }

    // Надіслати повідомлення в чат
    @PostMapping("/{chatId}/messages")
    public Message sendMessage(@PathVariable Integer chatId,
                               @RequestBody Message message) {
        return chatService.sendMessage(chatId, message);
    }
}
