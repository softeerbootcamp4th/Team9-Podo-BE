package com.softeer.podo.verification.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimVerificationRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String phoneNum;
}
