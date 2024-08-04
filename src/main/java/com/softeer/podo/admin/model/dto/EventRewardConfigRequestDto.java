package com.softeer.podo.admin.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRewardConfigRequestDto {
	List<EventRewardDto> eventRewardList;
	EventWeightDto eventWeight;
}
