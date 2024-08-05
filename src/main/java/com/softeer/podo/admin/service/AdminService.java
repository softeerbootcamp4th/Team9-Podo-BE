package com.softeer.podo.admin.service;


import com.softeer.podo.admin.model.dto.*;
import com.softeer.podo.admin.model.dto.user.ArrivalUserDto;
import com.softeer.podo.admin.model.dto.user.ArrivalUserListDto;
import com.softeer.podo.admin.model.dto.mapper.AdminMapper;
import com.softeer.podo.admin.model.dto.mapper.UserMapper;
import com.softeer.podo.admin.model.dto.user.LotsUserListDto;
import com.softeer.podo.admin.model.entity.Event;
import com.softeer.podo.admin.model.entity.EventReward;
import com.softeer.podo.admin.model.exception.EventNotFoundException;
import com.softeer.podo.admin.repository.EventRepository;
import com.softeer.podo.admin.repository.EventRewardRepository;
import com.softeer.podo.admin.repository.EventWeightRepository;
import com.softeer.podo.user.model.entity.LotsUser;
import com.softeer.podo.user.repository.ArrivalUserRepository;
import com.softeer.podo.user.repository.LotsUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminService {
	private final EventRepository eventRepository;
	private final EventRewardRepository eventRewardRepository;
	private final EventWeightRepository eventWeightRepository;
	private final AdminMapper adminMapper;
	private final LotsUserRepository lotsUserRepository;
	private final ArrivalUserRepository arrivalUserRepository;
	private final UserMapper userMapper;

	@Transactional
	public EventListResponseDto getEventList() {
		return adminMapper.eventListToEventListResponseDto(eventRepository.findAll());
	}

	@Transactional
	public EventDto configArrivalEvent(EventConfigRequestDto dto) {
		Event arrivalEvent = eventRepository.findById(1L).orElseThrow(EventNotFoundException::new);

		arrivalEvent.setTitle(dto.getTitle());
		arrivalEvent.setDescription(dto.getDescription());
		arrivalEvent.setRepeatDay(dto.getRepeatDay());
		arrivalEvent.setRepeatTime(dto.getRepeatTime());
		arrivalEvent.setStartAt(dto.getStartAt());
		arrivalEvent.setEndAt(dto.getEndAt());
		arrivalEvent.setTagImage(dto.getTagImage());

		eventRepository.save(arrivalEvent);
		return adminMapper.EventToEventDto(arrivalEvent);
	}

	@Transactional
	public EventDto configLotsEvent(EventConfigRequestDto dto) {
		Event lotsEvent =  eventRepository.findById(2L).orElseThrow(EventNotFoundException::new);

		lotsEvent.setTitle(dto.getTitle());
		lotsEvent.setDescription(dto.getDescription());
		lotsEvent.setRepeatDay(dto.getRepeatDay());
		lotsEvent.setRepeatTime(dto.getRepeatTime());
		lotsEvent.setStartAt(dto.getStartAt());
		lotsEvent.setEndAt(dto.getEndAt());
		lotsEvent.setTagImage(dto.getTagImage());

		eventRepository.save(lotsEvent);
		return adminMapper.EventToEventDto(lotsEvent);
	}

	@Transactional
	public ArrivalUserListDto configArrivalEventReward(EventRewardConfigRequestDto dto) {
		Event arrivalEvent = eventRepository.findById(1L).orElseThrow(EventNotFoundException::new);

		List<EventReward> arrivalReward = eventRewardRepository.findByEvent(arrivalEvent);
		eventRewardRepository.deleteAllInBatch(arrivalReward);
		eventRewardRepository.flush(); // 즉시 데이터베이스에 반영

		for(EventRewardDto rewardDto : dto.getEventRewardList()) {
			eventRewardRepository.save(
					EventReward.builder()
							.event(arrivalEvent)
							.reward(rewardDto.getReward())
							.rewardRank(rewardDto.getRank())
							.numWinners(rewardDto.getNumWinners())
							.build()
			);
		}
		return getArrivalApplicationList();
	}

	@Transactional
	public LotsUserListDto configLotsEventReward(EventRewardConfigRequestDto dto) {
		Event lotsEvent = eventRepository.findById(2L).orElseThrow(EventNotFoundException::new);

		List<EventReward> lotsReward = eventRewardRepository.findByEvent(lotsEvent);
		eventRewardRepository.deleteAllInBatch(lotsReward);
		eventRewardRepository.flush(); // 즉시 데이터베이스에 반영

		for(EventRewardDto rewardDto : dto.getEventRewardList()) {
			eventRewardRepository.save(
					EventReward.builder()
							.event(lotsEvent)
							.reward(rewardDto.getReward())
							.rewardRank(rewardDto.getRank())
							.numWinners(rewardDto.getNumWinners())
							.build()
			);
		}

		lotsEvent.getEventWeight().setWeightCondition(dto.getEventWeight().getCondition());
		lotsEvent.getEventWeight().setTimes(dto.getEventWeight().getTimes());
		eventWeightRepository.save(lotsEvent.getEventWeight());

		return getLotsApplicationList();
	}

	@Transactional
	public ArrivalUserListDto getArrivalApplicationList() {
		ArrivalUserListDto arrivalUserListDto = userMapper.ArrivalUserListToArrivalUserListDto(arrivalUserRepository.findAll());
		//선착순 이벤트 id
		Event arrivalEvent = eventRepository.findById(1L).orElseThrow(EventNotFoundException::new);
		List<EventReward> eventRewardList = arrivalEvent.getEventRewardList();
		// 보상 순위 기준으로 정렬
		eventRewardList.sort(Comparator.comparingInt(EventReward::getRewardRank));

		for(ArrivalUserDto arrivalUserDto : arrivalUserListDto.getApplicationList()){
			int base = 0; //누적 등수
			for (EventReward eventReward : eventRewardList) {
				//해당 상품을 받을 수 있는 등수이면
				if (arrivalUserDto.getRank() - base <= eventReward.getNumWinners()) {
					arrivalUserDto.setReward(eventReward.getReward());
					break;
				}else arrivalUserDto.setReward("");
				base += eventReward.getNumWinners();
			}
		}
		return arrivalUserListDto;
	}

	@Transactional
	public LotsUserListDto getLotsApplicationList() {
		return userMapper.LotsUserListToLotsUserListDto(lotsUserRepository.findAll());
	}

	@Transactional
	public LotsUserListDto getLotsResult(){
		//랜덤 추첨 이벤트
		Event lotsEvent = eventRepository.findById(2L).orElseThrow(EventNotFoundException::new);
		//보상 리스트
		List<EventReward> eventRewardList = lotsEvent.getEventRewardList();
		//응모 목록
		List<LotsUser> lotsUserList = lotsUserRepository.findAll();

		//comment에 대한 가중치
		int weight = lotsEvent.getEventWeight().getTimes();
		//전체 가중치합
		int totalWeight = 0;
		for(LotsUser lotsUser : lotsUserList){
			if(lotsUser.getLotsComment() != null){
				totalWeight += weight;
			}else totalWeight++;
		}

		ArrayList<Boolean> userCheckList = new ArrayList<>(Collections.nCopies(lotsUserList.size(), false));
		eventRewardList.sort(Comparator.comparingInt(EventReward::getRewardRank));
		for(EventReward eventReward : eventRewardList){
			//해당 reward 추첨
			for(int winCount = 0; winCount < eventReward.getNumWinners() && !lotsUserList.isEmpty(); winCount++){
				long currentTimeMillis = System.currentTimeMillis();
				Random random = new Random(currentTimeMillis);
				int randomInt = random.nextInt(totalWeight); // 랜덤 정수

				int weightSum = 0;
				for(int i = 0; i < lotsUserList.size(); i++){
					if(userCheckList.get(i)){ continue; }
					LotsUser lotsUser = lotsUserList.get(i);
					weightSum += lotsUser.getLotsComment() != null ? weight : 1;
					if(randomInt < weightSum){
						lotsUser.setReward(eventReward.getReward());
						lotsUserRepository.save(lotsUser);
						userCheckList.set(i, true);
						totalWeight -= lotsUser.getLotsComment() != null ? weight : 1;
						break;
					}
				}
			}
		}

		for(int i = 0; i < lotsUserList.size(); i++) {
			if(userCheckList.get(i)){ continue; }
			lotsUserList.get(i).setReward("");
			lotsUserRepository.save(lotsUserList.get(i));
		}

		return getLotsApplicationList();
	}
}
