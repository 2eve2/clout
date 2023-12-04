package com.mmm.clout.chatservice.chatRoom.exception;

import com.mmm.clout.chatservice.common.exception.CustomBaseException;
import com.mmm.clout.chatservice.common.exception.ErrorCode;

public class NotFoundRoomException extends CustomBaseException {
    public NotFoundRoomException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NotFoundRoomException() {
        super(ErrorCode.NOT_FOUND_CHATROOM);
    }
}
