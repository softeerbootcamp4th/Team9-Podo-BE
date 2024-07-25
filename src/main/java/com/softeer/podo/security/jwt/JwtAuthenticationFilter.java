package com.softeer.podo.security.jwt;

import com.nimbusds.jwt.JWTClaimsSet;
import com.softeer.podo.security.jwt.exception.EmptyTokenException;
import com.softeer.podo.security.jwt.exception.InvalidTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Authorization 헤더에서 토큰 추출
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (bearerToken!=null) {
            // 토큰 추출 후
            String jwtToken = tokenProvider.resolveToken(bearerToken);

            if (jwtToken != null) {
                // 토큰에서 Claim 추출 후 Attribute에 저장
                JWTClaimsSet claimsSet = tokenProvider.validateTokenAndGetClaimsSet(jwtToken);
                tokenProvider.setAttributesFromClaim(request, claimsSet);
            } else {
                throw new InvalidTokenException("토큰이 유효하지 않습니다.");
            }
        } else {
            throw new EmptyTokenException("Authorization 헤더에 토큰이 존재하지 않습니다.");
        }
        filterChain.doFilter(request, response);
    }
}