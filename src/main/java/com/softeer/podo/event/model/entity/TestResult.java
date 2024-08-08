package com.softeer.podo.event.model.entity;


import com.softeer.podo.event.util.Result;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "test_results")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Result result;

    private String type; // 유형
    private String description; // 유형 설명 문구
    private String subtitle1; //소제목
    private String subtitle2;
    private String subtitle3;
    private String scenario1; // 시나리오
    private String scenario2;
    private String scenario3;
    private String image1; // 이미지 s3 링크
    private String image2;
    private String image3;
    private String url;
}
