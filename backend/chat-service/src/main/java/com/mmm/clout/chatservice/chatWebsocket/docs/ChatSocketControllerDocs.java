package com.mmm.clout.chatservice.chatWebsocket.docs;

import com.mmm.clout.chatservice.chatWebsocket.dto.ChatHistoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Tag(name = "채팅목록 CRUD", description = "채팅목록 삭제 및 조회 api")
public interface ChatSocketControllerDocs {

    @Operation(summary = "채팅 목록만 가져오기",
        responses =
        @ApiResponse(responseCode = "200", description = "채팅 내역 리스트 제공",
            content =
            @Content(mediaType="application/json",
                schema=@Schema(implementation= ChatHistoryResponse.class))
        )
    )
    public ResponseEntity<List<ChatHistoryResponse>> getHistories(@PathVariable("roomId") String roomId);


    @Operation(summary = "채팅목록 삭제",
        responses =
        @ApiResponse(responseCode = "200", description = "채팅 목록 삭제",
            content =
            @Content(mediaType="application/json",
                schema=@Schema(implementation= Integer.class))
        )
    )
    public ResponseEntity<Integer> deleteHistories(@PathVariable("roomId") String roomId);
}
