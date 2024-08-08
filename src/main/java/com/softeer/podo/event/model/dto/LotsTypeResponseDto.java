package com.softeer.podo.event.model.dto;


import com.softeer.podo.event.util.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotsTypeResponseDto {
    private Long resultId;
    private Result result;
    private String type; // 유형
    private String description; // 유형 설명 문구
    private ArrayList<ScenarioDto> scenarioList;
}
