package com.softeer.podo.admin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EventListResponseDto {
	List<EventDto> eventList;
}
