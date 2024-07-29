package com.softeer.podo.common.utils;

import java.util.Random;

public class NumberUtils {

    /**
     * 8자리 난수 생성하기
     * @return 난수
     */
    public static String generateRandom8DigitNumber() {
        Random random = new Random();
        int min = 10_000_000;
        int max = 99_999_999;

        int randomNumber = random.nextInt((max-min)+1)+min;

        return String.valueOf(randomNumber);
    }
}
