package com.softeer.podo.admin.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.softeer.podo.common.entity.DateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private EventType eventType;

    private String title;
    private String description;

    /**
     * 형식은 7자리 0과 1로 이루어진 문자열. 월화수목금토일 의미
     */
    @Column(name = "repeat_day")
    private String repeatDay;
    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "repeat_time")
    private LocalDateTime repeatTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "start_time")
    private LocalDateTime startAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "end_time")
    private LocalDateTime endAt;

    @Column(name = "tag_image")
    private String tagImage;

    @OneToMany(mappedBy = "event" , orphanRemoval = true, cascade = CascadeType.ALL)
    private List<EventReward> eventRewardList;

    @OneToOne(mappedBy = "event" , orphanRemoval = true, cascade = CascadeType.ALL)
    private EventWeight eventWeight;
}
