package com.softeer.podo.verification.facade;

import com.softeer.podo.security.jwt.TokenInfo;
import com.softeer.podo.security.jwt.TokenProvider;
import com.softeer.podo.user.model.dto.UserDto;
import com.softeer.podo.user.service.UserService;
import com.softeer.podo.verification.exception.DuplicatePhoneNumException;
import com.softeer.podo.verification.exception.TokenNotMatchException;
import com.softeer.podo.verification.model.dto.CheckVerificationRequestDto;
import com.softeer.podo.verification.model.dto.CheckVerificationResponseDto;
import com.softeer.podo.verification.model.dto.ClaimVerificationRequestDto;
import com.softeer.podo.verification.service.MessageService;
import com.softeer.podo.verification.service.VerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class VerificationFacade {

    private final MessageService messageService;
    private final UserService userService;
    private final VerificationService verificationService;
    private final TokenProvider tokenProvider;

    @Transactional
    public void claimVerificationCode(ClaimVerificationRequestDto dto) {
        if(userService.checkUserAlreadyExists(dto.getPhoneNum())) {
            throw new DuplicatePhoneNumException("이미 응모한 전화번호입니다.");
        }
        String createdCode = verificationService.createAndSaveCode(dto.getName(), dto.getPhoneNum());
//        messageService.sendVerificationMessage(dto.getPhoneNum(), createdCode);
        log.info("created code for {} = {}", dto.getName(), createdCode);
    }

    @Transactional
    public CheckVerificationResponseDto checkVerification(CheckVerificationRequestDto dto) {
        if(userService.checkUserAlreadyExists(dto.getPhoneNum())) {
            throw new DuplicatePhoneNumException("이미 응모한 전화번호입니다.");
        }
        if(verificationService.getAuthInfo(dto.getName(), dto.getPhoneNum(), dto.getVerificationCode())) {
            UserDto savedUser = userService.saveUser(dto.getName(), dto.getPhoneNum());
            TokenInfo token = tokenProvider.createAccessToken(savedUser.getName(), savedUser.getPhoneNum(), savedUser.getRole());

            return new CheckVerificationResponseDto(
                    token.getToken(), token.getExpireTime()
            );
        } else {
            throw new TokenNotMatchException("전송한 토큰이 일치하지 않습니다.");
        }
    }
}
