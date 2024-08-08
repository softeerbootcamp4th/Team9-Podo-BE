package com.softeer.podo.event.util;

import com.softeer.podo.event.exception.InvalidSelectionException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * 유형검사 결과용 클래스
 */
@Component
public class SelectionMap {
    private final Map<String, Result> selectionMap = new HashMap<String, Result>();

    @PostConstruct
    public void init() {
        selectionMap.put("BBBB", Result.SAFETY);
        selectionMap.put("BBAB", Result.SAFETY);
        selectionMap.put("BBAA", Result.SAFETY);
        selectionMap.put("BAAA", Result.SAFETY);

        selectionMap.put("AAAA", Result.ADVENTURE);
        selectionMap.put("AAAB", Result.ADVENTURE);
        selectionMap.put("ABAA", Result.ADVENTURE);
        selectionMap.put("BAAB", Result.ADVENTURE);

        selectionMap.put("ABAB", Result.SENS);
        selectionMap.put("ABBA", Result.SENS);
        selectionMap.put("ABBB", Result.SENS);
        selectionMap.put("BBBA", Result.SENS);

        selectionMap.put("AABA", Result.EARLY_ADOPTER);
        selectionMap.put("AABB", Result.EARLY_ADOPTER);
        selectionMap.put("BABA", Result.EARLY_ADOPTER);
        selectionMap.put("BABB", Result.EARLY_ADOPTER);
    }


    /**
     * selection에 대해 유형테스트 결과를 반환
     * @param selection 선택한목록을 하나의 string으로 concat한 string
     * @return result enum
     * @throws InvalidSelectionException 선택형식이 잘못되었을때
     */
    public Result getResult(String selection) throws InvalidSelectionException {
        if(!selectionMap.containsKey(selection)) {
            throw new InvalidSelectionException("선택 형식이 잘못되었습니다.");
        }
        return selectionMap.get(selection);
    }
}

