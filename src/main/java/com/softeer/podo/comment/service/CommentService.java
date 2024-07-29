package com.softeer.podo.comment.service;

import com.softeer.podo.comment.model.dto.CommentPostRequestDto;
import com.softeer.podo.comment.model.dto.CommentPostResponseDto;
import com.softeer.podo.comment.model.entity.Comment;
import com.softeer.podo.comment.repository.CommentRepository;
import com.softeer.podo.common.response.CommonResponse;
import com.softeer.podo.security.Auth;
import com.softeer.podo.security.AuthInfo;
import com.softeer.podo.user.model.entity.User;
import com.softeer.podo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.*;
import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class CommentService {

    CommentRepository commentRepository;
    UserRepository userRepository;

    public CommentPostResponseDto postComment(AuthInfo authInfo, CommentPostRequestDto dto) {

        User user = userRepository.findByNameAndPhoneNum(authInfo.getName(), authInfo.getPhoneNum());

        Comment comment = Comment.builder()
                .user(user)
                .comment(dto.getComment())
                .build();
        commentRepository.save(comment);

        return new CommentPostResponseDto(comment);
    }
}
