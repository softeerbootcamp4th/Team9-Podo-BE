package com.softeer.podo.common.utils;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class URLUtils {

    /**
     * 주어진 문자열을 URL 인코딩합니다.
     *
     * @param value 인코딩할 문자열
     * @return URL 인코딩된 문자열
     */
    public static String encode(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    /**
     * 주어진 URL 인코딩된 문자열을 디코딩합니다.
     *
     * @param value 디코딩할 문자열
     * @return URL 디코딩된 문자열
     * @throws UnsupportedEncodingException 디코딩이 지원되지 않는 경우 발생
     */
    public static String decode(String value) throws UnsupportedEncodingException {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        try {
            String uniqueLink = "V1a1Vrqe1oBYCkqkXs7/Uw==";
            String encodedLink = encode(uniqueLink);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
