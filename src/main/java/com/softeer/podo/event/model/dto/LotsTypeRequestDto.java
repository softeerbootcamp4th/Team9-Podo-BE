package com.softeer.podo.event.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LotsTypeRequestDto {
    @NotBlank(message = "1번 답이 없습니다.")
    @Pattern(regexp = "^[AB]$", message = "답안 형식에 맞지 않습니다.")
    private String answer1;
    @NotBlank(message = "2번 답이 없습니다.")
    @Pattern(regexp = "^[AB]$", message = "답안 형식에 맞지 않습니다.")
    private String answer2;
    @NotBlank(message = "3번 답이 없습니다.")
    @Pattern(regexp = "^[AB]$", message = "답안 형식에 맞지 않습니다.")
    private String answer3;
    @NotBlank(message = "4번 답이 없습니다.")
    @Pattern(regexp = "^[AB]$", message = "답안 형식에 맞지 않습니다.")
    private String answer4;

    @JsonIgnore
    public String getSelection(){
        return answer1 + answer2 + answer3 + answer4;
    }
}
