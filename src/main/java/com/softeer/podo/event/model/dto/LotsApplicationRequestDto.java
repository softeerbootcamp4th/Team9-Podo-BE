package com.softeer.podo.event.model.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LotsApplicationRequestDto {
    @NotBlank
    private String answer1;
    @NotBlank
    private String answer2;
    @NotBlank
    private String answer3;
    @NotBlank
    private String answer4;

    public String getSelection(){
        return answer1 + answer2 + answer3 + answer4;
    }
}