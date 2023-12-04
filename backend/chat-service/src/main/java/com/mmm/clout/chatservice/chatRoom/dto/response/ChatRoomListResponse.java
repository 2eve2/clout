package com.mmm.clout.chatservice.chatRoom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ChatRoomListResponse {

    private List<ChatRoomResponse> chatHistoryList;

}
