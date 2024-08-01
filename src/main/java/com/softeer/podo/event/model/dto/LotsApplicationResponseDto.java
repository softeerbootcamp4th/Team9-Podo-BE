package com.softeer.podo.event.model.dto;


import com.softeer.podo.event.model.entity.TestResult;
import com.softeer.podo.event.util.Result;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LotsApplicationResponseDto {
    private Result result;

    private String type; // 유형
    private String description; // 유형 설명 문구
    private ArrayList<Senario> senarioList;


    public static class LotsApplicationResponseDtoBuilder {
        public LotsApplicationResponseDtoBuilder addSenario(String title, String subtitle, String image) {
            if(this.senarioList == null) {this.senarioList = new ArrayList<Senario>();}
            this.senarioList.add(new Senario(title, subtitle, image));
            return this;
        }
    }

    @Data
    @AllArgsConstructor
    private static class Senario{
        private String title;
        private String subtitle;
        private String image;
    }
}
