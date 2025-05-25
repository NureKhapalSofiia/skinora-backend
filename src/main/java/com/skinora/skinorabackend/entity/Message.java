package com.skinora.skinorabackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="chat_messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="chat_id", nullable = false)
    private Chat chat;

    @ManyToOne
    @JoinColumn(name="sender_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String message;

    @Column(name="sent_at",nullable = false)
    private LocalDateTime sentAt;

    @Column(name="is_read", nullable = false)
    private boolean isRead;

}
