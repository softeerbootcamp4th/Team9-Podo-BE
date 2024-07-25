package com.softeer.podo.security;

import com.softeer.podo.user.model.entity.Role;
import com.softeer.podo.security.jwt.exception.InvalidTokenException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Auth.class) && parameter.getParameterType() == AuthInfo.class;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        String name = (String) request.getAttribute("name");
        String number = (String) request.getAttribute("number");
        Role role = Role.valueOf((String)request.getAttribute("ROLE_"));

        if (name == null || number == null) {
            throw new InvalidTokenException("Claim에서 추출한 name, number 혹은 role이 올바르지 않습니다.");
        }

        return new AuthInfo(name, number, role);
    }
}
