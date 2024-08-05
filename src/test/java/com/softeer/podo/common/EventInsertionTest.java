package com.softeer.podo.common;


import com.softeer.podo.admin.model.entity.Event;
import com.softeer.podo.admin.model.entity.EventReward;
import com.softeer.podo.admin.model.entity.EventType;
import com.softeer.podo.admin.model.entity.EventWeight;
import com.softeer.podo.admin.repository.EventRepository;
import com.softeer.podo.admin.repository.EventRewardRepository;
import com.softeer.podo.admin.repository.EventWeightRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class EventInsertionTest {
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private EventWeightRepository eventWeightRepository;
	@Autowired
	private EventRewardRepository eventRewardRepository;

	@Test
	@DisplayName("선착순 Event정보 입력용 테스트")
	public void insertArrivalEventTest() {
		Event event = eventRepository.save(
				Event.builder()
						.eventType(EventType.builder().type("arrival").build())
						.title("셀토스 선착순 이벤트")
						.description("The 2025 셀토스 출시 기념 선착순 이벤트")
						.repeatDay("1111100")
						.repeatTime(LocalDateTime.of(2024, 9, 6, 15, 0, 0))
						.startAt(LocalDateTime.of(2024, 9, 6, 0, 0, 0))
						.endAt(LocalDateTime.of(2024, 9, 9, 18, 0, 0))
						.tagImage("image url")
						.build()
		);

		List<EventReward> eventRewards = new ArrayList<>();
		eventRewards.add(
				EventReward.builder()
						.numWinners(100)
						.rewardRank(1)
						.reward("스타벅스 커피 쿠폰")
						.event(event)
						.build()
		);

		eventRewardRepository.saveAll(eventRewards);


		assertNotNull(event);
	}



	@Test
	@DisplayName("랜덤추첨 Event정보 입력용 테스트")
	public void insertLotsEventTest() {
		Event event = eventRepository.save(
				Event.builder()
						.eventType(EventType.builder().type("lots").build())
						.title("셀토스 추첨 이벤트")
						.description("The 2025 셀토스 출시 기념 추첨 이벤트")
						.startAt(LocalDateTime.of(2024, 9, 6, 0, 0, 0))
						.endAt(LocalDateTime.of(2024, 9, 9, 18, 0, 0))
						.tagImage("image url")
						.build()
		);

		List<EventReward> eventRewards = new ArrayList<>();
		eventRewards.add(
				EventReward.builder()
						.numWinners(1)
						.rewardRank(1)
						.reward("시그니엘 숙박권")
						.event(event)
						.build()
		);
		eventRewards.add(
				EventReward.builder()
						.numWinners(3)
						.rewardRank(2)
						.reward("파인다이닝 식사권")
						.event(event)
						.build()
		);
		eventRewards.add(
				EventReward.builder()
						.numWinners(10)
						.rewardRank(3)
						.reward("현대백화점 상품권")
						.event(event)
						.build()
		);

		eventRewardRepository.saveAll(eventRewards);

		eventWeightRepository.save(
				EventWeight.builder()
						.times(3)
						.weightCondition("기대평")
						.event(event)
						.build()
		);


		assertNotNull(event);
	}

}
