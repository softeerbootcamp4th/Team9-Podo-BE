package com.softeer.podo.admin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ArrivalUserListDto {
	List<ArrivalUserDto> applicationList;
}
