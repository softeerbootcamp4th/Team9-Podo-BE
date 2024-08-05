package com.softeer.podo.admin.model.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArrivalUserDto {
	private Long id;
	private String name;
	private String phoneNum;
	private int rank;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;
	private String reward;
}
