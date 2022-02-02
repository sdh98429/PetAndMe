package com.sns.pet.controller;

import com.sns.pet.dto.UserDto;
import com.sns.pet.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("User 컨트롤러")
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private UserService userService;

    @ApiOperation(value = "회원가입", notes = "회원가입을 한다. DB 성공 여부에 따라 SUCCESS, FAIL 반환",response = String.class)
    @PostMapping
    public ResponseEntity<String> join(@RequestBody @ApiParam(value = "회원정보", required = true)UserDto userDto) throws Exception{
        logger.info("join - 호출");
        if(userService.join(userDto)){
            return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);

    }

    @ApiOperation(value = "회원정보 조회", notes = "회원정보를 조회한다.", response = String.class)
    @GetMapping("/{userNumber}")
    public ResponseEntity<UserDto> userDetails(@PathVariable("userNumber") @ApiParam(value = "조회할 회원번호") long userNumber) throws Exception{
        logger.info("userDetails 호출");
        UserDto userDto = userService.userInfo(userNumber);
        if(userDto != null){
            return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
        }
        return new ResponseEntity<UserDto>(userDto, HttpStatus.NO_CONTENT);
    }


    @ApiOperation(value = "회원정보 수정", notes ="회원정보를 수정 한다. DB 성공 여부에 따라 SUCCESS, FAIL 반환", response = String.class)
    @PutMapping
    public ResponseEntity<String> userModify(@RequestBody @ApiParam(value = "수정할 회원정보") UserDto userDto) throws Exception{
        logger.info("userModify 호출");
        if(userService.userModify(userDto)){
            System.out.println("성공");
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
            System.out.println("실패");
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "회원 탈퇴", notes ="회원 탈퇴, DB 성공 여부에 따라 SUCCESS, FAIL 반환", response = String.class)
    @DeleteMapping("/{userNumber}")
    public ResponseEntity<String> userRemove(@PathVariable("userNumber") @ApiParam(value = "탈퇴할 회원번호") long userNumber) throws Exception{
        logger.info("userRemove 호출 ");
        if(userService.userRemove(userNumber)){
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

}
