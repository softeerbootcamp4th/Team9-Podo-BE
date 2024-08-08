package com.softeer.podo.event.model.entity;

import com.softeer.podo.admin.model.entity.LotsUser;
import com.softeer.podo.common.entity.DateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "event_lots_share_links")
@AllArgsConstructor
@NoArgsConstructor
public class LotsShareLink extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private LotsUser lotsUser;

    private Long count;
    private String shareLink;

    public void increaseCount() {
        this.count++;
    }
}
