package com.softeer.podo.admin.controller;

import com.softeer.podo.admin.model.dto.user.ArrivalUserListDto;
import com.softeer.podo.admin.model.dto.EventListResponseDto;
import com.softeer.podo.admin.service.AdminService;
import com.softeer.podo.common.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
	private final AdminService adminService;


	@GetMapping("/eventlist")
	@Operation(summary = "이벤트 목록 반환 Api")
	public CommonResponse<EventListResponseDto> eventList(){
		return new CommonResponse<>(adminService.getEventList());
	}

	@GetMapping("/arrival/applicationList")
	@Operation(summary = "선착순 응모 인원 반환 Api")
	public CommonResponse<ArrivalUserListDto> arrivalApplicationList(){
		return new CommonResponse<>(adminService.getArrivalApplicationList());
	}

}
