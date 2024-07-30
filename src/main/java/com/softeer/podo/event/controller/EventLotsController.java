package com.softeer.podo.event.controller;


import com.softeer.podo.comment.model.entity.Comment;
import com.softeer.podo.common.response.CommonResponse;
import com.softeer.podo.event.model.dto.LotsApplicationRequestDto;
import com.softeer.podo.event.model.dto.LotsApplicationResponseDto;
import com.softeer.podo.event.service.EventLotsService;
import com.softeer.podo.security.Auth;
import com.softeer.podo.security.AuthInfo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/lots")
@RequiredArgsConstructor
@Slf4j
public class EventLotsController {

    private final EventLotsService eventLotsService;

    @PostMapping("/application")
    @Operation(summary = "유형테스트 결과 제출  Api")
    public CommonResponse<LotsApplicationResponseDto> eventApplication(@Auth AuthInfo authInfo,
                                                                       @Valid @RequestBody  LotsApplicationRequestDto dto) {
        return new CommonResponse<>(eventLotsService.application(authInfo, dto));
    }
}
