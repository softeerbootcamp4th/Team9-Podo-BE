package com.softeer.podo.event.service;


import com.softeer.podo.admin.model.entity.Role;
import com.softeer.podo.common.utils.AESUtils;
import com.softeer.podo.common.utils.URLUtils;
import com.softeer.podo.event.exception.*;
import com.softeer.podo.event.model.dto.*;
import com.softeer.podo.event.model.dto.mapper.LotsEventMapper;
import com.softeer.podo.event.model.entity.LotsComment;
import com.softeer.podo.event.model.entity.LotsShareLink;
import com.softeer.podo.event.model.entity.TestResult;
import com.softeer.podo.event.repository.LotsCommentRepository;
import com.softeer.podo.event.repository.LotsShareLinkRepository;
import com.softeer.podo.event.repository.TestResultRepository;
import com.softeer.podo.event.util.Result;
import com.softeer.podo.event.util.SelectionMap;
import com.softeer.podo.security.AuthInfo;
import com.softeer.podo.admin.model.entity.LotsUser;
import com.softeer.podo.admin.repository.LotsUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventLotsService {

    private final LotsUserRepository lotsUserRepository;
    private final TestResultRepository testResultRepository;
    private final LotsCommentRepository lotsCommentRepository;
    private final LotsShareLinkRepository lotsShareLinkRepository;

    private final SelectionMap selectionMap;
    private final LotsEventMapper lotsEventMapper;

    @Value("${server.host}")
    private String SERVER_HOST;
    @Value("${server.port}")
    private String SERVER_PORT;


    /**
     * 랜덤추천 이벤트에서 적절한 드라이버 타입 반환
     * @param dto 선택지 정보
     * @return 유형테스트 결과
     */
    @Transactional(readOnly = true)
    public LotsTypeResponseDto getProperDriverType(LotsTypeRequestDto dto)  {
        // 유형 선택
        Result result = selectionMap.getResult(dto.getSelection());
        TestResult testResult = testResultRepository.findByResult(result);

        return lotsEventMapper.TestResultToApplicationDto(testResult);
    }


    /**
     * 랜덤 추첨 이벤트 응모 후 공유링크 반환
     * @param dto 드라이버 타입
     * @return 공유링크
     */
    @Transactional
    public LotsApplicationResponseDto applyEvent(AuthInfo authInfo, LotsApplicationRequestDto dto)  {
        if(lotsUserRepository.existsByPhoneNum(authInfo.getPhoneNum())){
            throw new ExistingUserException("이미 이벤트에 응모한 유저입니다.");
        }

        // 유형 찾기
        TestResult testResult = testResultRepository.findById(dto.getResultTypeId())
                .orElseThrow(() -> new InvalidResultTypeException("잘못된 Result Type 아이디입니다."));

        // 유저 저장
        LotsUser savedUser = lotsUserRepository.save(
                LotsUser.builder()
                        .name(authInfo.getName())
                        .phoneNum(authInfo.getPhoneNum())
                        .role(Role.ROLE_USER)
                        .testResult(testResult)
                        .build()
        );

        // 고유 링크 생성
        String uniqueLink;
        try {
             uniqueLink = createUniqueLink(savedUser.getId());
        } catch (Exception e) {
            throw new AESExecutionException("userId {"+savedUser.getId()+"} 암호화 과정 중 오류가 발생했습니다.");
        }

        // 공유정보 생성
        lotsShareLinkRepository.save(
                new LotsShareLink(null, savedUser, 0L, uniqueLink)
        );

        return new LotsApplicationResponseDto(uniqueLink);
    }


    /**
     * 랜덤추천 이벤트 응모자의 기대평을 등록
     * @param authInfo 사용자 정보
     * @param dto 기대평 내용
     * @return 등록결과
     */
    @Transactional
    public LotsCommentResponseDto registerComment(AuthInfo authInfo, LotsCommentRequestDto dto)  {
        // 사용자가 이벤트에 아직 응모하지 않았을때
        if(!lotsUserRepository.existsByPhoneNum(authInfo.getPhoneNum())){
            throw new UserNotExistException("해당 사용자가 아직 이벤트에 응모하지 않았습니다.");
        }

        // TODO("여기에 comment 내용 체크용 로직 구현")

        LotsUser lotsUser = lotsUserRepository.findByPhoneNum(authInfo.getPhoneNum())
                .orElseThrow(() -> new ExistingUserException("이미 이벤트에 응모한 유저입니다."));

        // 이미 comment가 존재할때
        if(lotsCommentRepository.existsByLotsUser(lotsUser)) {
            throw new ExistingCommentException("이미 기대평을 작성했습니다.");
        }

        LotsComment comment = LotsComment.builder()
                .lotsUser(lotsUser)
                .comment(dto.getComment())
                .build();
        lotsCommentRepository.save(comment);

        return new LotsCommentResponseDto(comment);
    }


    /**
     * 사용자의 고유 링크를 받고 복호화한 후 유형 링크 반환
     * @param uniqueLink 사용자의 고유 링크
     * @return 유형 페이지 url
     */
    @Transactional
    public String getEventUrl(String uniqueLink) throws Exception {
//        String decodedUniqueLink = URLUtils.decode(uniqueLink);
        Long userId = Long.parseLong(AESUtils.decrypt(uniqueLink));
        LotsUser findUser = lotsUserRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("유저가 존재하지 않습니다."));

        // 해당 유저의 공유수 증가
        LotsShareLink findUserShareLink = lotsShareLinkRepository.findByLotsUser(findUser)
                .orElseThrow(() -> new LotsShareLinkNotExistsException("해당 유저의 공유링크 정보가 존재하지 않습니다."));
        findUserShareLink.increaseCount();

        // 해당 유저의 유형 링크 반환
        return findUser.getTestResult().getUrl();
    }


    private String createUniqueLink(Long userId) throws Exception {
        return SERVER_HOST + ":" + SERVER_PORT + "/lots/link/" + URLUtils.encode(AESUtils.encrypt(String.valueOf(userId)));
    }
}
