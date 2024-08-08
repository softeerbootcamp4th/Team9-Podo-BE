package com.softeer.podo.admin.model.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventConfigRequestDto {

	@NotBlank(message = "title이 존재하지 않습니다.")
	private String title;

	@NotBlank(message = "description이 존재하지 않습니다.")
	private String description;

	@Pattern(regexp="^[01]{7}$", message = "반복일 형식에 맞지 않습니다.")
	private String repeatDay;

	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime repeatTime;

	@NotNull(message = "startAt이 존재하지 않습니다.")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startAt;

	@NotNull(message = "endAt이 존재하지 않습니다.")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endAt;

	private String tagImage;
}
