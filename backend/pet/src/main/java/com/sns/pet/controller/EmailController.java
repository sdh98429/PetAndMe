package com.sns.pet.controller;

import com.sns.pet.dto.EmailDto;
import com.sns.pet.service.EmailService;
import com.sns.pet.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("Email 인증 컨트롤러")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String DUPLICATE = "duplicate";

    private final EmailService emailService;

    @ApiOperation(value = "회원가입 인증메일 보내기", notes = "이메일의 중복을 확인 후, 메일을 보내고, DB에 인증키 정보를 저장한다. 중복시 DUPLICATE, 성공시 SUCCESS 반환")
    @PostMapping("/sendEmail/{userEmail}")
    public ResponseEntity<String> sendEmail(@PathVariable("userEmail") @ApiParam(value = "이메일 주소") String userEmail) throws Exception {
        logger.info("sendEmail 호출");
        String authKey = emailService.findAuthKey(userEmail);
        if (authKey != null && authKey.equals("authorization")) {
            return new ResponseEntity<String>(DUPLICATE, HttpStatus.OK);
        } else {
            authKey = emailService.sendSimpleMessage(userEmail);
            if(authKey.equals("error")){
             return new ResponseEntity<String>("email error", HttpStatus.OK);
            }
            emailService.addAuthKey(userEmail, authKey);
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "이메일 인증", notes = "입력한 인증키와 저장된 인증키가 같은지 비교 후, 같다면 SUCCESS, 다르면 FAIL 반환")
    @PostMapping("/emailConfirm")
    public ResponseEntity<String> emailConfirm(@RequestBody EmailDto emailDto) throws Exception {
        logger.info("emailConfirm 호출");
        String userEmail = emailDto.getUserEmail();
        String authKey = emailDto.getAuthKey();
        if (authKey.equals(emailService.findAuthKey(userEmail))) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }
}
