package com.sns.pet.controller;

import com.sns.pet.dto.UserDto;
import com.sns.pet.service.JwtServiceImpl;
import com.sns.pet.service.LoginService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class LoginController {

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final JwtServiceImpl jwtService;
    private final LoginService loginService;

    @ApiOperation(value = "로그인", notes = "userID와 userPW를 통해 로그인을 시도합니다.")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @ApiParam(value = "회원 객체", required = true) @RequestBody Map<String, String> user) {

        logger.info("login 호출");
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            UserDto loginUser = loginService.findByIdAndPw(user);

            if (loginUser != null) {
                // 토큰 생성
                String token = jwtService.create("loginUser", loginUser, "access-token");// key, data, subject
                logger.debug("로그인 토큰정보 : {}", token);
                resultMap.put("accessToken", token);
                resultMap.put("message", SUCCESS);
            } else { // 아이디 또는 비번을 틀림
                loginUser = loginService.findById(user.get("userID")); // 아이디로 회원 찾기
                if(loginUser != null) {
                    resultMap.put("message", "로그인 실패 : 비밀번호가 틀렸습니다.");
                } else {
                    resultMap.put("message", "로그인 실패 : 가입하지 않은 아이디입니다.");
                }
            }
            status = HttpStatus.ACCEPTED; // 202
        } catch (Exception e) {
            logger.error("로그인 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR; // 500 서버에 문제가 생김
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
