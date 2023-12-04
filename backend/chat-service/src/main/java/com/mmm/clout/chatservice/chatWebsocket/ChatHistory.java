package com.mmm.clout.chatservice.chatWebsocket;

import jakarta.persistence.EntityListeners;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Document(collection = "chat_history")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class ChatHistory {

    @Id
    private String id;

    @Field("room_idx")
    private String roomIdx;

    @Field("sender_name")
    private String senderName;

    @Field("sender_uuid")
    private String senderId;

    @Field("msg")
    private String msg;

    @Field("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Field("updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    private ChatHistory(String id, String roomIdx, String senderName, String senderId, String msg,
                        LocalDateTime createdAt, LocalDateTime updatedAt) {

        this.id = id;
        this.roomIdx = roomIdx;
        this.senderName = senderName;
        this.senderId = senderId;
        this.msg = msg;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ChatHistory of(String roomIdx, String senderName, String senderId, String msg) {

        return ChatHistory.builder()
                .roomIdx(roomIdx)
                .senderName(senderName)
                .senderId(senderId)
                .msg(msg)
                .build();
    }

}