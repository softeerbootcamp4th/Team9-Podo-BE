package com.softeer.podo.admin.model.entity;

import com.softeer.podo.common.entity.DateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "testusers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestUser extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="testuser_id")
    private Long id; //고유 pk

    private String name;
    private String phoneNum;
    private Role role;
}