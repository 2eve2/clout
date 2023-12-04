package com.mmm.clout.chatservice.chatWebsocket;

import com.mmm.clout.chatservice.chatWebsocket.dto.ChatRequest;
import com.mmm.clout.chatservice.chatWebsocket.dto.ChatHistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatSocketService {

    private final ChatSocketRepository chatSocketRepository;

    public ChatHistoryResponse recordHistory(final String chatRoomNo, final ChatRequest request) {

        final ChatHistory save = chatSocketRepository.save(
                ChatHistory.of(chatRoomNo, request.sender(), request.senderId(),
                        request.msg())
        );
        return ChatHistoryResponse.of(save);
    }

    public List<ChatHistoryResponse> getList(String roomId) {
        return chatSocketRepository.findAllByRoomIdx(roomId).stream()
                .map(chatHistory -> ChatHistoryResponse.of(chatHistory))
                .toList();
    }

    public Integer deleteChat(String roomId) {
        return chatSocketRepository.deleteAllByRoomIdx(roomId);
    }
}