package com.mmm.clout.chatservice.chatRoom.dto.response;
import com.mmm.clout.chatservice.chatRoom.ChatRoom;
import com.mmm.clout.chatservice.chatWebsocket.dto.ChatHistoryResponse;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ChatRoomDetailResponse {

    private Long chatRoomId;

    private Long advertisementId;

    private Long hostId;

    private Long guestId;

    private List<ChatHistoryResponse> chatHistories;

    public static ChatRoomDetailResponse of(ChatRoom room) {
        ChatRoomDetailResponse newRoom = ChatRoomDetailResponse.builder()
            .chatRoomId(room.getId())
            .advertisementId(room.getAdvertisementId())
            .hostId(room.getHostId())
            .guestId(room.getGuestId())
            .build();
        return newRoom;
    }
}
