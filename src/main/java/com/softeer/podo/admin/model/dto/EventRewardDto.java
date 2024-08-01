package com.softeer.podo.admin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventRewardDto{
	private int rank;
	private int numWinners;
	private String reward;
}
