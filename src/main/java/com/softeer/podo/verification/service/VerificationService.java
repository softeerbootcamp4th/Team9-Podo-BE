package com.softeer.podo.verification.service;

import com.softeer.podo.verification.model.dto.CheckVerificationRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class VerificationService {

    /**
     * 랜덤 키를 생성해서 키-인증번호 쌍을 Redis에 저장한다.
     * @return 생성된 인증번호
     */
    @Transactional
    public String createAndSaveCode() {
        // TODO("Redis에 저장 - key는 (이름+전화번호)+(암호화), value는 인증번호, 만료시간(3min) 설정)
        return "103892";
    }

    /**
     * 인증번호가 서버에 저장된 인증번호와 일치하는지 여부를 체크한다.
     */
    @Transactional
    public boolean getAuthInfo(CheckVerificationRequestDto dto) {
        // TODO("redis에서 (이름+전화번호)를 통해서 code를 찾고, dto로 전달받은 코드와 일치하는지 비교한다.)
        return true;
    }
}
