package com.mmm.clout.memberservice.clouter.presentation.docs;

import com.mmm.clout.memberservice.clouter.presentation.request.CreateClrRequest;
import com.mmm.clout.memberservice.clouter.presentation.request.UpdateClrRequest;
import com.mmm.clout.memberservice.clouter.presentation.response.CreateClrResponse;
import com.mmm.clout.memberservice.clouter.presentation.response.SelectClrResponse;
import com.mmm.clout.memberservice.clouter.presentation.response.SelectTop10Response;
import com.mmm.clout.memberservice.clouter.presentation.response.UpdateClrResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "클라우터 CRUD", description = "클라우터 회원가입, 수정, 조회를 제공하는 api")
public interface ClouterControllerDocs {


    @Operation(summary = "클라우터 회원가입",
        responses =
        @ApiResponse(responseCode = "201", description = "생성된 클라우터 id 리턴",
            content =
            @Content(mediaType="application/json",
                schema=@Schema(implementation=CreateClrResponse.class))
        )
    )
    public ResponseEntity<CreateClrResponse> create(
        @RequestBody CreateClrRequest createClrRequest
    );

    @Operation(summary = "클라우터 수정",
        parameters = {
            @Parameter(
                in = ParameterIn.HEADER,
                name = "Authorization",
                required = true,
                schema = @Schema(type = "string"),
                description = "인증 토큰"
            )
        },
        responses =
        @ApiResponse(responseCode = "200", description = "수정된 클라우터 id 리턴",
            content =
            @Content(mediaType="application/json",
                schema=@Schema(implementation=UpdateClrResponse.class))
        )
    )
    public ResponseEntity<UpdateClrResponse> update(
        @PathVariable("clouterId") Long clouterId,
        @RequestBody UpdateClrRequest updateClrRequest
    );


    @Operation(summary = "클라우터 단건 조회",
        parameters = {
            @Parameter(
                in = ParameterIn.HEADER,
                name = "Authorization",
                required = true,
                schema = @Schema(type = "string"),
                description = "인증 토큰"
            )
        },
        responses =
        @ApiResponse(responseCode = "200", description = "클라우터 상세 정보 리스폰스",
            content =
            @Content(mediaType="application/json",
                schema=@Schema(implementation=SelectClrResponse.class))
        )
    )
    public ResponseEntity<SelectClrResponse> selectClouter(
        @PathVariable("clouterId") Long clouterId
    );

    @Operation(summary = "클라우터 top10 조회",
        parameters = {
            @Parameter(
                in = ParameterIn.HEADER,
                name = "Authorization",
                required = true,
                schema = @Schema(type = "string"),
                description = "인증 토큰"
            )
        },
        responses =
        @ApiResponse(responseCode = "200", description = "클라우터 top10 리스트 리턴",
            content =
            @Content(mediaType="application/json",
                schema=@Schema(implementation=SelectTop10Response.class))
        )
    )
    public ResponseEntity<SelectTop10Response> getClouterTop10();
}
