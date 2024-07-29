package com.softeer.podo.event.model.dto;


import com.softeer.podo.event.model.entity.TestResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotsApplicationResponseDto {
    private TestResult testResults;
}
