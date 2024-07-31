package com.softeer.podo.verification.controller;

import com.softeer.podo.common.response.CommonResponse;
import com.softeer.podo.verification.facade.VerificationFacade;
import com.softeer.podo.verification.model.dto.CheckVerificationRequestDto;
import com.softeer.podo.verification.model.dto.CheckVerificationResponseDto;
import com.softeer.podo.verification.model.dto.ClaimVerificationRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/verification")
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationFacade verificationFacade;

    @PostMapping("/claim")
    @Operation(summary = "전화번호로 인증번호 요청")
    public CommonResponse<String> claimVerificationCode(@Valid @RequestBody ClaimVerificationRequestDto dto) {
        verificationFacade.claimVerificationCode(dto);
        return new CommonResponse<>("인증번호 발송 완료");
    }

    @PostMapping("/check")
    @Operation(summary = "인증번호 검증")
    public CommonResponse<CheckVerificationResponseDto> checkVerification(@Valid @RequestBody CheckVerificationRequestDto dto) {
        return new CommonResponse<>(verificationFacade.checkVerification(dto));
    }
}
