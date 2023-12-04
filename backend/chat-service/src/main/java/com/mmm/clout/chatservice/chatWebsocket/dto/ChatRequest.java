package com.mmm.clout.chatservice.chatWebsocket.dto;

public record ChatRequest(
        String sender,
        String senderId,
        String msg
) {
}
