package com.skinora.skinorabackend.repository;

import com.skinora.skinorabackend.entity.Chat;
import com.skinora.skinorabackend.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findBySenderIdAndReceiverId(Integer senderId, Integer receiverId);
    List<Message> findByChatOrderByTimestampAsc(Chat chat);

}
