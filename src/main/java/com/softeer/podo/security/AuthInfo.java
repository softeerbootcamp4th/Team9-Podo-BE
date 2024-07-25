package com.softeer.podo.security;

import com.softeer.podo.event.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Data
public class AuthInfo {
    private String name;
    private String phoneNum;
    private Role role;
}
