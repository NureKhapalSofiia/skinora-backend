package com.skinora.skinorabackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="chats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name="participant_1_id", nullable = false)
    private Patient participant1;

    @ManyToOne
    @JoinColumn(name="participant_1_id", nullable = false)
    private Doctor participant2;
}
