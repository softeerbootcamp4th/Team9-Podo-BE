package com.softeer.podo.admin.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventWeightDto{

	@Min(value = 1, message = "가중치 배수는 1 이상이어야 합니다.")
	private int times;

	@NotBlank(message = "가중치 조건이 존재하지 않습니다.")
	private String condition;
}
