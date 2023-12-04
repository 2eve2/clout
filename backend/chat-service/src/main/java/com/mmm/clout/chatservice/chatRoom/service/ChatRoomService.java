package com.mmm.clout.chatservice.chatRoom.service;

import com.mmm.clout.chatservice.chatRoom.ChatRoom;
import com.mmm.clout.chatservice.chatRoom.dto.response.ChatRoomDetailResponse;
import com.mmm.clout.chatservice.chatRoom.dto.request.ChatRoomRequest;
import com.mmm.clout.chatservice.chatRoom.dto.response.ChatRoomListResponse;
import com.mmm.clout.chatservice.chatRoom.dto.response.CreateChatRoomResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatRoomService {

    CreateChatRoomResponse makeRoom(ChatRoomRequest request);

    ChatRoomDetailResponse enterRoom(Long roomId);

    ChatRoomListResponse getChatRoomByAdvertiser(Long advertiserId);

    ChatRoomListResponse getChatRoomByClouter(Long clouterId);
}
