package com.softeer.podo.event.service;

import com.softeer.podo.admin.model.entity.LotsUser;
import com.softeer.podo.admin.model.entity.Role;
import com.softeer.podo.admin.repository.LotsUserRepository;
import com.softeer.podo.event.model.dto.*;
import com.softeer.podo.event.model.entity.LotsComment;
import com.softeer.podo.event.repository.LotsCommentRepository;
import com.softeer.podo.event.repository.LotsShareLinkRepository;
import com.softeer.podo.security.AuthInfo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventLotsServiceTest {
	@Autowired
	private EventLotsService eventLotsService;

	@Autowired
	LotsUserRepository lotsUserRepository;

	@Autowired
	LotsCommentRepository lotsCommentRepository;

	@Autowired
	LotsShareLinkRepository lotsShareLinkRepository;

	private static String name;
	private static String phoneNum;
	private static Long resultId;
	private static String comment;

	@BeforeAll
	static void setUp() {
		name = "test_user";
		phoneNum = "123456789";
		resultId = 3L;
		comment = "test_comment";
	}

	@Test
	@Transactional
	@DisplayName("유형테스트 결과 제출 controller 테스트")
	void getProperDriverType() {
		//given
		LotsTypeRequestDto requestDto = new LotsTypeRequestDto();
		requestDto.setAnswer1("A");
		requestDto.setAnswer2("B");
		requestDto.setAnswer3("A");
		requestDto.setAnswer4("B");


		//when
		LotsTypeResponseDto responseDto = eventLotsService.getProperDriverType(requestDto);

		//then
		assertNotNull(responseDto.getScenarioList());
		assertNotNull(responseDto.getResultId());
	}

	@Test
	@Transactional
	@DisplayName("이벤트 응모 테스트")
	void applyEvent() {
		//given
		AuthInfo authInfo = new AuthInfo(name, phoneNum, Role.ROLE_USER);
		LotsApplicationRequestDto requestDto = new LotsApplicationRequestDto(resultId);

		//when
		LotsApplicationResponseDto responseDto = eventLotsService.applyEvent(authInfo, requestDto);

		//then
		LotsUser lotsUser = lotsUserRepository.findByPhoneNum(phoneNum).orElseThrow();
		assertNotNull(lotsShareLinkRepository.findByLotsUser(lotsUser).orElseThrow());
		assertNotNull(responseDto.getUniqueLink());
	}

	@Test
	@Transactional
	@DisplayName("comment 등록 테스트")
	void registerComment() {
		//given
		AuthInfo authInfo = new AuthInfo(name, phoneNum, Role.ROLE_USER);
		LotsApplicationRequestDto applyDto = new LotsApplicationRequestDto(resultId);
		eventLotsService.applyEvent(authInfo, applyDto);
		LotsCommentRequestDto requestDto = new LotsCommentRequestDto(comment);

		//when
		LotsCommentResponseDto responseDto = eventLotsService.registerComment(authInfo, requestDto);

		//then
		LotsComment lotsComment = lotsCommentRepository.findById(responseDto.getComment().getId()).orElseThrow();
		assertEquals(lotsComment.getComment(), comment);
	}

	@Test
	@Transactional
	@DisplayName("이벤트 목록 service")
	void getEventUrl() throws Exception {
		//given
		AuthInfo authInfo = new AuthInfo(name, phoneNum, Role.ROLE_USER);
		LotsApplicationRequestDto requestDto = new LotsApplicationRequestDto(resultId);
		String link = URLDecoder.decode(eventLotsService.applyEvent(authInfo, requestDto).getUniqueLink(), StandardCharsets.UTF_8);
		String uniqueLink = link.substring(link.indexOf("/lots/link/") + "/lots/link/".length());
		LotsUser lotsUser = lotsUserRepository.findByPhoneNum(phoneNum).orElseThrow();

		//when
		String resultUrl = eventLotsService.getEventUrl(uniqueLink);

		//then
		assertEquals(lotsUser.getTestResult().getUrl(), resultUrl);
	}
}