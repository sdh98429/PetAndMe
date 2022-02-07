package com.sns.pet.service;

import com.sns.pet.dto.JoinDto;
import com.sns.pet.dto.UserDto;
import com.sns.pet.dto.UserPetDto;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    // 회원가입
    boolean addUser (JoinDto joinDto) throws Exception;
    // 회원조회
    UserDto findUser(Long userNumber) throws Exception;
    // 회원수정
    boolean modifyUser(UserDto userDto) throws Exception;
    // 회원탈퇴
    boolean removeUser(Long userNumber) throws Exception;
    // 회원정보 전송
    UserPetDto findUserInfo(Long userNumber) throws Exception;
    // 회원번호 전송
    UserDto findUserNumber(String userID) throws Exception;
}
