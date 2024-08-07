package com.softeer.podo.admin.service;

import com.softeer.podo.admin.model.dto.*;
import com.softeer.podo.admin.model.dto.mapper.UserMapper;
import com.softeer.podo.admin.model.dto.user.ArrivalUserDto;
import com.softeer.podo.admin.model.dto.user.ArrivalUserListDto;
import com.softeer.podo.admin.model.dto.user.LotsUserListDto;
import com.softeer.podo.admin.model.entity.Event;
import com.softeer.podo.admin.repository.EventRepository;
import com.softeer.podo.admin.repository.EventRewardRepository;
import com.softeer.podo.event.model.dto.LotsApplicationResponseDto;
import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


@SpringBootTest
class AdminServiceTest {
	@Autowired
	private AdminService adminService;
	@Autowired
	private EventRewardRepository eventRewardRepository;
	@Autowired
	private EventRepository eventRepository;

	@Test
	@Transactional
	@DisplayName("이벤트 목록 service")
	void getEventList() {
		//given

		//when
		EventListResponseDto responseDto = adminService.getEventList();

		//then
		assertEquals(2, responseDto.getEventList().size());
	}

	@Test
	@Transactional
	@DisplayName("선착순 이벤트 수정 service")
	void configArrivalEvent() {
		//given
		String title = "test";
		String description = "testDescription";
		String repeatDay = "1111100";
		LocalTime repeatTime = LocalTime.of(13, 00);
		LocalDateTime startAt = LocalDateTime.of(2024, 9, 6, 13, 00);
		LocalDateTime endAt = LocalDateTime.of(2024, 9, 6, 13, 00);
		String tagImage = "image url";
		EventConfigRequestDto requestDto = new EventConfigRequestDto(title, description, repeatDay, repeatTime, startAt, endAt, tagImage);

		//when
		EventDto responseDto = adminService.configArrivalEvent(requestDto);

		//then
		assertEquals(title, responseDto.getTitle());
		assertEquals(description, responseDto.getDescription());
		assertEquals(repeatDay, responseDto.getRepeatDay());
		assertEquals(repeatTime, responseDto.getRepeatTime());
		assertEquals(startAt, responseDto.getStartAt());
		assertEquals(endAt, responseDto.getEndAt());
		assertEquals(tagImage, responseDto.getTagImage());
	}

	@Test
	@Transactional
	@DisplayName("랜덤 추첨 이벤트 수정 service")
	void configLotsEvent() {
		//given
		String title = "test";
		String description = "testDescription";
		String repeatDay = "1111100";
		LocalTime repeatTime = LocalTime.of(13, 00);
		LocalDateTime startAt = LocalDateTime.of(2024, 9, 6, 13, 00);
		LocalDateTime endAt = LocalDateTime.of(2024, 9, 6, 13, 00);
		String tagImage = "image url";
		EventConfigRequestDto requestDto = new EventConfigRequestDto(title, description, repeatDay, repeatTime, startAt, endAt, tagImage);

		//when
		EventDto responseDto = adminService.configLotsEvent(requestDto);

		//then
		assertEquals(title, responseDto.getTitle());
		assertEquals(description, responseDto.getDescription());
		assertEquals(repeatDay, responseDto.getRepeatDay());
		assertEquals(repeatTime, responseDto.getRepeatTime());
		assertEquals(startAt, responseDto.getStartAt());
		assertEquals(endAt, responseDto.getEndAt());
		assertEquals(tagImage, responseDto.getTagImage());
	}

	@Test
	@Transactional
	@DisplayName("선착순 이벤트 보상 수정 service")
	void configArrivalEventReward() {
		//given
		int rewardNum = 5;

		List<EventRewardDto> eventRewardList = new ArrayList<>();
		for(int i = 1 ; i <= rewardNum ; i++) {
			EventRewardDto rewardDto = new EventRewardDto(i, i, "reward" + i);
			eventRewardList.add(rewardDto);
		}
		EventRewardConfigRequestDto requestDto = new EventRewardConfigRequestDto();
		requestDto.setEventRewardList(eventRewardList);

		//when
		ArrivalUserListDto responseDto = adminService.configArrivalEventReward(requestDto);

		//then
		Event arrivalEvent = eventRepository.findById(1L).orElseThrow();
		assertEquals(rewardNum, arrivalEvent.getEventRewardList().size());

		for(int i = 0; i < responseDto.getApplicationList().size(); i++){  //보상 확인
			ArrivalUserDto user = responseDto.getApplicationList().get(i);
			int rank = user.getRank();

			int winSum = 0;
			for(EventRewardDto eventRewardDto : eventRewardList){
				winSum += eventRewardDto.getNumWinners();
				if(rank <= winSum){
					assertEquals(eventRewardDto.getReward(), user.getReward());
					break;
				}
			}
		}
	}

	@Test
	@Transactional
	@DisplayName("랜덤추첨 이벤트 보상 수정 service")
	void configLotsEventReward() {
		//given
		int rewardNum = 5;

		List<EventRewardDto> eventRewardList = new ArrayList<>();
		for(int i = 1 ; i <= rewardNum ; i++) {
			EventRewardDto rewardDto = new EventRewardDto(i, i, "reward" + i);
			eventRewardList.add(rewardDto);
		}
		EventRewardConfigRequestDto requestDto = new EventRewardConfigRequestDto();
		requestDto.setEventRewardList(eventRewardList);
		requestDto.setEventWeight(new EventWeightDto(3, "comment"));


		//when
		LotsUserListDto responseDto = adminService.configLotsEventReward(requestDto);

		//then
		Event arrivalEvent = eventRepository.findById(2L).orElseThrow();
		assertEquals(rewardNum, arrivalEvent.getEventRewardList().size());
	}

	@Test
	void getArrivalApplicationList() {
	}

	@Test
	void getLotsApplicationList() {
	}

	@Test
	void getLotsResult() {
	}
}