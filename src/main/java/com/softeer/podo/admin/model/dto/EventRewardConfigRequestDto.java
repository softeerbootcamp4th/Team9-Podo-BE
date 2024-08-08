package com.softeer.podo.admin.model.dto;

import com.softeer.podo.admin.model.entity.validation.ValidationGroups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

	@Valid
	@NotNull(message = "가중치가 비어있습니다.", groups = ValidationGroups.LotsRewardValidation.class)
	EventWeightDto eventWeight;
}
