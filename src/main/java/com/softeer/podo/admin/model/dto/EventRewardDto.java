package com.softeer.podo.admin.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventRewardDto{

	@Min(value = 1, message = "상품 등급은 1 이상이어야 합니다.")
	private int rank;

	@Min(value = 1, message = "당첨자 수는 1 이상이어야 합니다.")
	private int numWinners;

	@NotBlank(message = "상품이 존재하지 않습니다.")
	private String reward;
}
