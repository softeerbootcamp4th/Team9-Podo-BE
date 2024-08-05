package com.softeer.podo.event.service;


import com.softeer.podo.event.exception.ExistingCommentException;
import com.softeer.podo.event.exception.ExistingUserException;
import com.softeer.podo.event.exception.UserNotExistException;
import com.softeer.podo.event.model.dto.LotsApplicationRequestDto;
import com.softeer.podo.event.model.dto.LotsApplicationResponseDto;
import com.softeer.podo.event.model.dto.LotsCommentRequestDto;
import com.softeer.podo.event.model.dto.LotsCommentResponseDto;
import com.softeer.podo.event.model.dto.mapper.LotsEventMapper;
import com.softeer.podo.event.model.entity.LotsComment;
import com.softeer.podo.event.model.entity.TestResult;
import com.softeer.podo.event.repository.LotsCommentRepository;
import com.softeer.podo.event.repository.TestResultRepository;
import com.softeer.podo.event.util.Result;
import com.softeer.podo.event.util.SelectionMap;
import com.softeer.podo.security.AuthInfo;
import com.softeer.podo.user.model.entity.LotsUser;
import com.softeer.podo.user.repository.LotsUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventLotsService {
    private final LotsUserRepository lotsUserRepository;
    private final SelectionMap selectionMap;
    private final TestResultRepository testResultRepository;
    private final LotsCommentRepository lotsCommentRepository;

    private final LotsEventMapper lotsEventMapper;

    /**
     * 랜덤추천 이벤트 응모 api
     * @param authInfo 사용자 토큰 정보
     * @param dto 선택지 정보
     * @return 유형테스트 결과
     */
    @Transactional
    public LotsApplicationResponseDto applyLotsEvent(AuthInfo authInfo, LotsApplicationRequestDto dto)  {

        if(lotsUserRepository.existsByPhoneNum(authInfo.getPhoneNum())){
            throw new ExistingUserException("이미 존재하는 전화번호입니다.");
        }

        Result result = selectionMap.getResult(dto.getSelection());


        LotsUser lotsUser = LotsUser.builder()
                .name(authInfo.getName())
                .phoneNum(authInfo.getPhoneNum())
                .role(authInfo.getRole())
                .reward("")
                .build();

        lotsUserRepository.save(lotsUser);

        TestResult testResult = testResultRepository.findByResult(result);
        return lotsEventMapper.TestResultToApplicationDto(testResult);
    }

    /**
     * 랜덤추천 이벤트 응모자의 기대평을 등록
     * @param authInfo 사용자 정보
     * @param dto 기대평 내용
     * @return 등록결과
     */
    @Transactional
    public LotsCommentResponseDto comment(AuthInfo authInfo, LotsCommentRequestDto dto)  {
        //사용자가 이벤트에 아직 응모하지 않았을때
        if(!lotsUserRepository.existsByPhoneNum(authInfo.getPhoneNum())){
            throw new UserNotExistException("해당 사용자가 아직 이벤트에 응모하지 않았습니다.");
        }

        // 여기에 comment 내용 체크용 로직 구현

        LotsUser lotsUser = lotsUserRepository.findByNameAndPhoneNum(authInfo.getName(), authInfo.getPhoneNum());

        //이미 comment가 존재할때
        if(lotsCommentRepository.existsByLotsUser(lotsUser)){
            throw new ExistingCommentException("이미 기대평을 작성했습니다.");
        }

        LotsComment comment = LotsComment.builder()
                .lotsUser(lotsUser)
                .comment(dto.getComment())
                .build();
        lotsCommentRepository.save(comment);


        return new LotsCommentResponseDto(comment);
    }
}
