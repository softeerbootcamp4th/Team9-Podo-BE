package com.softeer.podo.comment.model.entity;


import com.softeer.podo.user.model.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "event_lots_comments")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content")
    private String comment;
}
