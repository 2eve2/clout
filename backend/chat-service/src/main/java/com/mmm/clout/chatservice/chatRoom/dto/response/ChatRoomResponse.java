package com.mmm.clout.chatservice.chatRoom.dto.response;
import com.mmm.clout.chatservice.chatRoom.ChatRoom;
import com.mmm.clout.chatservice.chatWebsocket.dto.ChatHistoryResponse;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ChatRoomResponse {

    private Long chatRoomId;

    private Long advertisementId;

    private Long hostId;

    private Long guestId;

    public static ChatRoomResponse of(ChatRoom room) {
        ChatRoomResponse newRoom = ChatRoomResponse.builder()
            .chatRoomId(room.getId())
            .advertisementId(room.getAdvertisementId())
            .hostId(room.getHostId())
            .guestId(room.getGuestId())
            .build();
        return newRoom;
    }
}
