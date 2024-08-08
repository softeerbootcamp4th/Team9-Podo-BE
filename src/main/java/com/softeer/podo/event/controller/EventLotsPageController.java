package com.softeer.podo.event.controller;

import com.softeer.podo.event.service.EventLotsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/lots")
@Controller
@RequiredArgsConstructor
public class EventLotsPageController {

    private final EventLotsService eventLotsService;

    /**
     * 고유 공유링크 클릭
     * {uniqueLink}: 유저 id (암호화)
     */
    @GetMapping("/link/{uniqueLink}")
    @Operation(summary = "공유링크 클릭시 redirection하기 위함 (사용자 직접 호출용)")
    public String shareLinkClick(
            HttpServletResponse response,
            @PathVariable String uniqueLink
    ) {
        try {
            // 해당 유저에 해당하는 적절한 이벤트 결과 페이지 찾기
            String redirectUrl = eventLotsService.getEventUrl(uniqueLink);
            // 이벤트 결과 페이지 반환
//            response.setStatus(HttpServletResponse.SC_FOUND);
//            response.setHeader("Location", "/type/"+redirectUrl);
            return "/type/"+redirectUrl;
        } catch (Exception e) {
            // 에러 페이지로 리다이렉션
//            response.setStatus(HttpServletResponse.SC_FOUND);
//            response.setHeader("Location", "/error/404");
            return "/error/404";
        }
    }
}
