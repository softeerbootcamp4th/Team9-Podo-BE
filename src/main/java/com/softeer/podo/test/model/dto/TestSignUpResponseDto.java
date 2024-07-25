package com.softeer.podo.test.model.dto;

import com.softeer.podo.event.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestSignUpResponseDto {
    private Long id;
    private String name;
    private String phoneNum;
    private Role role;
    private String accessToken;
    private Long accessTokenRemainTime;
}
