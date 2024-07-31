package com.softeer.podo.user.service;

import com.softeer.podo.user.model.dto.UserDto;
import com.softeer.podo.user.model.entity.Role;
import com.softeer.podo.user.model.entity.User;
import com.softeer.podo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public boolean checkUserAlreadyExists(String phoneNum) {
        return userRepository.existsByPhoneNum(phoneNum);
    }

    @Transactional
    public UserDto saveUser(String name, String phoneNum) {
        User savedUser = userRepository.save(new User(null, name, phoneNum, Role.ROLE_USER));
        return new UserDto(savedUser.getId(), savedUser.getName(), savedUser.getPhoneNum(), savedUser.getRole());
    }
}
