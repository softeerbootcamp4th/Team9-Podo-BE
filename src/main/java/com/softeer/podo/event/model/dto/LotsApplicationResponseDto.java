package com.softeer.podo.event.model.dto;


import com.softeer.podo.event.model.entity.TestResult;
import com.softeer.podo.event.util.Result;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LotsApplicationResponseDto {
    private Result result;

    private String type; // 유형
    private String description; // 유형 설명 문구
    private String subtitle1; //소제목
    private String subtitle2;
    private String subtitle3;
    private String senario1; // 시나리오
    private String senario2;
    private String senario3;
    private String image1; // 이미지 s3 링크
    private String image2;
    private String image3;
}
