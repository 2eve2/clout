package com.mmm.clout.chatservice.chatRoom.exception;

import com.mmm.clout.chatservice.common.exception.CustomBaseException;
import com.mmm.clout.chatservice.common.exception.ErrorCode;

public class ExistedRoomException extends CustomBaseException {
    public ExistedRoomException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ExistedRoomException() {
        super(ErrorCode.EXITED_ROOM_ERROR);
    }
}
