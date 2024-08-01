package com.softeer.podo.admin.model.entity;

import com.softeer.podo.common.entity.DateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "event_rewards")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventReward extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reward_id")
    private Long id;
    @Column(name = "reward_rank")
    private int rewardRank;
    /**
     * 당첨자수
     */
    @Column(name = "winner_number")
    private int numWinners;
    private String reward;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
}
