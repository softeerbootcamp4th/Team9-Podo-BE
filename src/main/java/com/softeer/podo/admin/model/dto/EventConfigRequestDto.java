package com.softeer.podo.admin.model.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventConfigRequestDto {

	private String title;
	private String description;
	private String repeatDay;
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalDateTime repeatTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startAt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endAt;
	private String tagImage;

}
