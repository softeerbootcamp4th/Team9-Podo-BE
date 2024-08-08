package com.softeer.podo.event.controller;

import com.softeer.podo.common.response.CommonResponse;
import com.softeer.podo.event.model.dto.LotsApplicationRequestDto;
import com.softeer.podo.event.model.dto.LotsApplicationResponseDto;
import com.softeer.podo.event.model.dto.LotsCommentRequestDto;
import com.softeer.podo.event.model.dto.LotsCommentResponseDto;
import com.softeer.podo.event.service.EventLotsService;
import com.softeer.podo.security.Auth;
import com.softeer.podo.security.AuthInfo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/lots")
@RestController
@RequiredArgsConstructor
public class EventLotsController {

    private final EventLotsService eventLotsService;

    @PostMapping("/comment")
    @Operation(summary = "랜덤추천이벤트 기대평 등록용 Api")
    public CommonResponse<LotsCommentResponseDto> eventComment(
            @Auth AuthInfo authInfo,
            @Valid @RequestBody LotsCommentRequestDto dto
    ) {
        return new CommonResponse<>(eventLotsService.registerComment(authInfo, dto));
    }

    @PostMapping("/application")
    @Operation(summary = "랜덤추첨 이벤트 응모하기")
    public CommonResponse<LotsApplicationResponseDto> applyEvent(
            @Auth AuthInfo authInfo,
            @Valid @RequestBody LotsApplicationRequestDto dto
    ) {
        return new CommonResponse<>(eventLotsService.applyEvent(authInfo, dto));
    }

}
