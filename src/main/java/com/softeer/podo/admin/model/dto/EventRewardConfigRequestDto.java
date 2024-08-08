package com.softeer.podo.admin.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRewardConfigRequestDto {
	@NotEmpty(message = "상품 리스트가 비어있습니다.")
	@Valid
	List<@Valid EventRewardDto> eventRewardList;
	EventWeightDto eventWeight;
}
