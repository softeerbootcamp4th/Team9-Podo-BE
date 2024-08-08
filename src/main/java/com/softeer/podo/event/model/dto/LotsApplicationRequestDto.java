package com.softeer.podo.event.model.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LotsApplicationRequestDto {
    @Min(value = 1, message = "결과 id가 잘못되었습니다.")
    @Max(value = 4, message = "결과 id가 잘못되었습니다.")
    private Long resultTypeId;
}
