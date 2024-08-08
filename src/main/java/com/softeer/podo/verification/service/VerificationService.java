package com.softeer.podo.verification.service;

import com.softeer.podo.common.utils.NumberUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
@Service
public class VerificationService {

    private final RedisService redisService;
    private final Duration EXPIRE_TIME = Duration.ofMinutes(3);

    /**
     * 랜덤 키를 생성해서 키-인증번호 쌍을 Redis에 저장한다. (인증번호 만료시간: 3min)
     * key = (이름+전화번호)
     * value = 랜덤으로 생성된 8자리 인증번호
     * @return 생성된 인증번호
     */
    @Transactional
    public String createAndSaveCode(String name, String phoneNum) {
        String key = name+phoneNum;
        String value = NumberUtils.generateRandom8DigitNumber();
        redisService.setValues(key, value, EXPIRE_TIME);
        return value;
    }

    /**
     * 인증번호가 redis에 저장된 이름+번호를 key로 하는 value값(인증번호)와 일치하는지 여부를 체크한다.
     * @param name 인증 요청 유저명
     * @param phoneNum 인증 요청 유저 번호
     * @param verificationCode 인증번호
     * @return 인증번호 일치 여부
     */
    @Transactional
    public boolean getAuthInfo(
            String name,
            String phoneNum,
            String verificationCode
    ) {
        //임시 만능 키
        if(verificationCode.equals("654321")) return true;
        String key = name+phoneNum;
        String value = redisService.getValues(key);
        return value.equals(verificationCode);
    }

}
