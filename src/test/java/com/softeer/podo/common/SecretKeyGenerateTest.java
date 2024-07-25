package com.softeer.podo.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerateTest {

    @Test
    @DisplayName("랜덤 16바이트 시크릿 키 생성")
    public void generate16ByteKey() {
        // 16바이트 비밀 키 생성
        byte[] keyBytes = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(keyBytes);

        // Base64 인코딩
        String base64Key = Base64.getEncoder().encodeToString(keyBytes);

        System.out.println("Generated Base64 Key: " + base64Key);
        System.out.println("Key length: " + keyBytes.length);
    }

    @Test
    @DisplayName("랜덤 32바이트 시크릿 키 생성")
    public void generate32ByteKey() {
        // 32바이트 비밀 키 생성
        byte[] keyBytes = new byte[32];
        SecureRandom random = new SecureRandom();
        random.nextBytes(keyBytes);

        // Base64 인코딩
        String base64Key = Base64.getEncoder().encodeToString(keyBytes);

        System.out.println("Generated Base64 Key: " + base64Key);
        System.out.println("Key length: " + keyBytes.length);
    }
}
