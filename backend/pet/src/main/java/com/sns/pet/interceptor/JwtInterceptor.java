package com.sns.pet.interceptor;

import com.sns.pet.exception.UnAuthorizedException;
import com.sns.pet.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    // JWT = JSON Web Token -> JSON 포맷을 이용한 Web Token
    public static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    private static final String HEADER_AUTH = "auth-token";

    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader(HEADER_AUTH);

        if(token != null && jwtService.isUsable(token)){
            logger.info("토큰 사용 가능 : {}", token);
            return true;
        }else{
            logger.info("토큰 사용 불가능 : {}", token);
            throw new UnAuthorizedException();
        }

    }
}
