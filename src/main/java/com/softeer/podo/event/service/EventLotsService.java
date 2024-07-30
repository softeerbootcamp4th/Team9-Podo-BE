package com.softeer.podo.event.service;


import com.softeer.podo.event.exception.ExistingUserException;
import com.softeer.podo.event.exception.InvalidSelectionException;
import com.softeer.podo.event.model.dto.LotsApplicationRequestDto;
import com.softeer.podo.event.model.dto.LotsApplicationResponseDto;
import com.softeer.podo.event.model.entity.TestResult;
import com.softeer.podo.event.repository.TestResultRepository;
import com.softeer.podo.event.util.Result;
import com.softeer.podo.event.util.SelectionMap;
import com.softeer.podo.security.AuthInfo;
import com.softeer.podo.user.model.entity.User;
import com.softeer.podo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventLotsService {
    private final UserRepository userRepository;
    private final SelectionMap selectionMap;
    private final TestResultRepository testResultRepository;


    /**
     * 랜덤추천 이벤트 응모 api
     * @param authInfo 사용자 토큰 정보
     * @param dto 선택지 정보
     * @return 유형테스트 결과
     */
    public LotsApplicationResponseDto application(AuthInfo authInfo, LotsApplicationRequestDto dto)  {

        if(userRepository.existsByphoneNum(authInfo.getPhoneNum())){
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

        return new LotsApplicationResponseDto(
            testResult
        );
    }
}
