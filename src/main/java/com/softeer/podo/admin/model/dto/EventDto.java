package com.softeer.podo.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class EventDto{
	private Long id;
	private String eventType;
	private String title;
	private String description;
	/**
	 * 형식은 7자리 0과 1로 이루어진 문자열. 월화수목금토일 의미
	 */
	@Nullable
	private String repeatDay;
	@JsonFormat(pattern = "HH:mm:ss")
	@Nullable
	private LocalTime repeatTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startAt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endAt;
	private String tagImage;

	private List<EventRewardDto> eventRewardList;
	@Nullable
	private EventWeightDto eventWeight;
}