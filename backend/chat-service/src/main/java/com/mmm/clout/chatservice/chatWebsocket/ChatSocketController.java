package com.mmm.clout.chatservice.chatWebsocket;

import com.mmm.clout.chatservice.chatWebsocket.docs.ChatSocketControllerDocs;
import com.mmm.clout.chatservice.chatWebsocket.dto.ChatRequest;
import com.mmm.clout.chatservice.chatWebsocket.dto.ChatHistoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ChatSocketController implements ChatSocketControllerDocs {

    private final ChatSocketService chatSocketService;

    @MessageMapping("/chat/{roomId}")
    @SendTo("/sub/chat/{roomId}")
    public ChatHistoryResponse SendMessage(final ChatRequest request,
                                           @DestinationVariable(value = "roomId") String chatRoomNo) throws Exception {
        log.info("roomId : {}, message : {}", chatRoomNo, request);
        return chatSocketService.recordHistory(chatRoomNo, request);
    }

    @GetMapping("/chat/{roomId}")
    public ResponseEntity<List<ChatHistoryResponse>> getHistories(@PathVariable("roomId") String roomId) {
        log.info("roomId : {}", roomId);
        List<ChatHistoryResponse> histories = chatSocketService.getList(roomId);
        return ResponseEntity.ok(histories);
    }

    @DeleteMapping("/chat/{roomId}")
    public ResponseEntity<Integer> deleteHistories(@PathVariable("roomId") String roomId) {
        log.info("roomId : {}", roomId);
        return ResponseEntity.ok(chatSocketService.deleteChat(roomId));
    }
}
