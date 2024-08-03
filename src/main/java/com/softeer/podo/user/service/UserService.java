package com.softeer.podo.user.service;

import com.softeer.podo.user.model.dto.UserDto;
import com.softeer.podo.user.model.entity.LotsUser;
import com.softeer.podo.user.model.entity.Role;
import com.softeer.podo.user.repository.LotsUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final LotsUserRepository lotsUserRepository;

    @Transactional(readOnly = true)
    public boolean checkUserAlreadyExists(String phoneNum) {
        return lotsUserRepository.existsByPhoneNum(phoneNum);
    }

    @Transactional
    public UserDto saveUser(String name, String phoneNum) {
        LotsUser savedLotsUser = lotsUserRepository.save(new LotsUser(null, name, phoneNum, "", Role.ROLE_USER));
        return new UserDto(savedLotsUser.getId(), savedLotsUser.getName(), savedLotsUser.getPhoneNum(), savedLotsUser.getRole());
    }
}
