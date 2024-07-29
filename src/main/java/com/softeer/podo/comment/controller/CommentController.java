package com.softeer.podo.comment.controller;


import com.softeer.podo.comment.model.dto.CommentPostRequestDto;
import com.softeer.podo.comment.model.dto.CommentPostResponseDto;
import com.softeer.podo.comment.service.CommentService;
import com.softeer.podo.common.response.CommonResponse;
import com.softeer.podo.security.Auth;
import com.softeer.podo.security.AuthInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @Transactional
    public CommonResponse<CommentPostResponseDto> postComment(@Auth AuthInfo authInfo , @Valid @RequestBody CommentPostRequestDto dto) {
        return new CommonResponse<>(commentService.postComment(authInfo, dto));
    }

}
