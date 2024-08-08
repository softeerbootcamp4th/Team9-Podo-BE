package com.softeer.podo.event.controller;

import com.softeer.podo.common.response.CommonResponse;
import com.softeer.podo.event.model.dto.LotsTypeRequestDto;
import com.softeer.podo.event.model.dto.LotsTypeResponseDto;
import com.softeer.podo.event.service.EventLotsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/lots")
@RestController
@RequiredArgsConstructor
public class EventLotsApiController {

    private final EventLotsService eventLotsService;

    /**
     * 제출한 유형테스트 결과에 따라 적절한 드라이버 유형 반환
     */
    @PostMapping("/type")
    @Operation(summary = "제출한 유형테스트 결과에 따라 적절한 드라이버 유형 반환")
    public CommonResponse<LotsTypeResponseDto> getDriverType(@Valid @RequestBody LotsTypeRequestDto dto) {
        return new CommonResponse<>(eventLotsService.getProperDriverType(dto));
    }
}
