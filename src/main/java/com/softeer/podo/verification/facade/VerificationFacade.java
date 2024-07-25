package com.softeer.podo.verification.facade;

import com.softeer.podo.security.jwt.TokenInfo;
import com.softeer.podo.security.jwt.TokenProvider;
import com.softeer.podo.user.model.entity.Role;
import com.softeer.podo.verification.exception.TokenNotMatchException;
import com.softeer.podo.verification.model.dto.CheckVerificationRequestDto;
import com.softeer.podo.verification.model.dto.CheckVerificationResponseDto;
import com.softeer.podo.verification.model.dto.ClaimVerificationRequestDto;
import com.softeer.podo.verification.service.MessageService;
import com.softeer.podo.verification.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class VerificationFacade {

    private final MessageService messageService;
    private final VerificationService verificationService;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public void claimVerificationCode(ClaimVerificationRequestDto dto) {
        String createdCode = verificationService.createAndSaveCode();
        messageService.sendVerificationMessage(dto.getPhoneNum(), createdCode);
    }

    @Transactional(readOnly = true)
    public CheckVerificationResponseDto checkVerification(CheckVerificationRequestDto dto) {
        if(verificationService.getAuthInfo(dto)) {
            TokenInfo token = tokenProvider.createAccessToken(dto.getName(), dto.getName(), Role.ROLE_USER);
            return new CheckVerificationResponseDto(
                    token.getToken(), token.getExpireTime()
            );
        } else {
            throw new TokenNotMatchException("전송한 토큰이 일치하지 않습니다.");
        }
    }
}
