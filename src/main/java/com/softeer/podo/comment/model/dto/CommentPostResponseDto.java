package com.softeer.podo.comment.model.dto;


import com.softeer.podo.comment.model.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentPostResponseDto {
    private Comment comment;
}
