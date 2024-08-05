package com.softeer.podo.user.model.entity;

import com.softeer.podo.common.entity.DateEntity;
import com.softeer.podo.event.model.entity.LotsComment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "event_lots_users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LotsUser extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String name;
    @Column(name = "phone_number", unique = true)
    private String phoneNum;
    private String reward;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(mappedBy = "lotsUser", orphanRemoval = true, cascade = CascadeType.ALL)
    private LotsComment lotsComment;
}
