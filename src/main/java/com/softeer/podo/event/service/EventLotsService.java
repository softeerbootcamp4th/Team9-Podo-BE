package com.softeer.podo.event.service;


import com.softeer.podo.event.exception.ExistingCommentException;
import com.softeer.podo.event.exception.ExistingUserException;
import com.softeer.podo.event.exception.UserNotExistException;
import com.softeer.podo.event.model.dto.LotsApplicationRequestDto;
import com.softeer.podo.event.model.dto.LotsApplicationResponseDto;
import com.softeer.podo.event.model.dto.LotsCommentRequestDto;
import com.softeer.podo.event.model.dto.LotsCommentResponseDto;
import com.softeer.podo.event.model.entity.LotsComment;
import com.softeer.podo.event.model.entity.TestResult;
import com.softeer.podo.event.repository.LotsCommentRepository;
import com.softeer.podo.event.repository.TestResultRepository;
import com.softeer.podo.event.util.Result;
import com.softeer.podo.event.util.SelectionMap;
import com.softeer.podo.security.AuthInfo;
import com.softeer.podo.user.model.entity.User;
import com.softeer.podo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventLotsService {
    private final UserRepository userRepository;
    private final SelectionMap selectionMap;
    private final TestResultRepository testResultRepository;
    private final LotsCommentRepository lotsCommentRepository;


    /**
     * 랜덤추천 이벤트 응모 api
     * @param authInfo 사용자 토큰 정보
     * @param dto 선택지 정보
     * @return 유형테스트 결과
     */
    @Transactional
    public LotsApplicationResponseDto applyArrivalEvent(AuthInfo authInfo, LotsApplicationRequestDto dto)  {

        if(userRepository.existsByPhoneNum(authInfo.getPhoneNum())){
            throw new ExistingUserException("이미 존재하는 전화번호입니다.");
        }

        Result result = selectionMap.getResult(dto.getSelection());


        User user = User.builder()
                .name(authInfo.getName())
                .phoneNum(authInfo.getPhoneNum())
                .role(authInfo.getRole())
                .build();

        userRepository.save(user);

        TestResult testResult = testResultRepository.findByResult(result);
        return LotsApplicationResponseDto.builder()
                .result(testResult.getResult())
                .type(testResult.getType())
                .description(testResult.getDescription())
                .subtitle1(testResult.getSubtitle1())
                .subtitle2(testResult.getSubtitle2())
                .subtitle3(testResult.getSubtitle3())
                .senario1(testResult.getSenario1())
                .senario2(testResult.getSenario2())
                .senario3(testResult.getSenario3())
                .image1(testResult.getImage1())
                .image2(testResult.getImage2())
                .image3(testResult.getImage3())
                .build();
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
        if(!userRepository.existsByPhoneNum(authInfo.getPhoneNum())){
            throw new UserNotExistException("해당 사용자가 아직 이벤트에 응모하지 않았습니다.");
        }

        // 여기에 comment 내용 체크용 로직 구현

        User user = userRepository.findByNameAndPhoneNum(authInfo.getName(), authInfo.getPhoneNum());

        //이미 comment가 존재할때
        if(lotsCommentRepository.existsByUser(user)){
            throw new ExistingCommentException("이미 기대평을 작성했습니다.");
        }

        LotsComment comment = LotsComment.builder()
                .user(user)
                .comment(dto.getComment())
                .build();
        lotsCommentRepository.save(comment);


        return new LotsCommentResponseDto(comment);
    }
}
