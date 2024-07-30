package com.softeer.podo.event.model.entity;


import com.softeer.podo.common.entity.DateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tests")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Test extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;
    private String question; // 질문
    private String answer1; // 답변1
    private String answer2; // 답변2
}
