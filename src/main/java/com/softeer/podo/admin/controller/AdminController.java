package com.softeer.podo.admin.controller;

import com.softeer.podo.admin.model.dto.EventConfigRequestDto;
import com.softeer.podo.admin.model.dto.EventDto;
import com.softeer.podo.admin.model.dto.EventRewardDto;
import com.softeer.podo.admin.model.dto.user.ArrivalUserListDto;
import com.softeer.podo.admin.model.dto.EventListResponseDto;
import com.softeer.podo.admin.model.dto.user.LotsUserListDto;
import com.softeer.podo.admin.service.AdminService;
import com.softeer.podo.common.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@PutMapping("/arrival/config")
	@Operation(summary = "선착순 이벤트 수정 Api")
	public CommonResponse<EventDto> arrivalEventConfig( @RequestBody EventConfigRequestDto dto){
		return new CommonResponse<>(adminService.configArrivalEvent(dto));
	}

	@PutMapping("/lots/config")
	@Operation(summary = "랜덤추첨 이벤트 수정 Api")
	public CommonResponse<EventDto> lotsEventConfig( @RequestBody EventConfigRequestDto dto){
		return new CommonResponse<>(adminService.configLotsEvent(dto));
	}

	@PutMapping("/arrival/rewardconfig")
	@Operation(summary = "선착순 이벤트 상품 수정 Api")
	public CommonResponse<ArrivalUserListDto> arrivalEventRewardConfig(@RequestBody List<EventRewardDto> dto){
		return new CommonResponse<>(adminService.configArrivalEventReward(dto));
	}

	@GetMapping("/arrival/applicationList")
	@Operation(summary = "선착순 응모 인원 반환 Api")
	public CommonResponse<ArrivalUserListDto> arrivalApplicationList(){
		return new CommonResponse<>(adminService.getArrivalApplicationList());
	}

	@GetMapping("/lots/applicationList")
	@Operation(summary = "랜덤추첨 응모 인원 반환 Api")
	public CommonResponse<LotsUserListDto> lotsApplicationList(){
		return new CommonResponse<>(adminService.getLotsApplicationList());
	}

}
