package com.mmm.clout.chatservice.chatRoom;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    List<ChatRoom> findAllByHostId(Long hostId);

    List<ChatRoom> findAllByGuestId(Long guestId);

    Optional<ChatRoom> findByHostIdAndGuestId(Long hostId, Long guestId);
}
