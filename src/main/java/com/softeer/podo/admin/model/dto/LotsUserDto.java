package com.softeer.podo.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kotlinx.datetime.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LotsUserDto {
	private Long id;
	private String name;
	private String phoneNum;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;
	private int rank;
	private String reward;
}