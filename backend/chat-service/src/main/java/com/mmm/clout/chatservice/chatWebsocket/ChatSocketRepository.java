package com.mmm.clout.chatservice.chatWebsocket;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatSocketRepository extends MongoRepository<ChatHistory, String> {

    List<ChatHistory> findAllByRoomIdx(String roomId);

    int deleteAllByRoomIdx(String roomId);
}
