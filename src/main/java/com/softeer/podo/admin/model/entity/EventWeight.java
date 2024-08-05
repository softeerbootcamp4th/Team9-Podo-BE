package com.softeer.podo.admin.model.entity;

import com.softeer.podo.common.entity.DateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "event_weights")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventWeight extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weight_id")
    private Long id;
    /**
     * 가중치 배수
     */
    private int times;
    @Column(name = "weight_condition")
    private String weightCondition;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name =  "event_id")
    private Event event;
}
