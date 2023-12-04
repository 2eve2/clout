package com.mmm.clout.chatservice.chatWebsocket.dto;


import com.mmm.clout.chatservice.chatWebsocket.ChatHistory;

import java.time.LocalDateTime;

public record ChatHistoryResponse(
        String chatIdx,
        String senderName,
        String senderId,
        String msg,
        LocalDateTime dateTime
) {

    public static ChatHistoryResponse of(final ChatHistory entity) {
        return new ChatHistoryResponse(
                entity.getId(),
                entity.getSenderName(),
                entity.getSenderId(),
                entity.getMsg(),
                entity.getCreatedAt()
        );
    }
}