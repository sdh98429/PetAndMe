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
    boolean modifyUser(JoinDto joinDto) throws Exception;
    // 회원탈퇴
    boolean removeUser(Long userNumber) throws Exception;
    // 프로필 사진 수정
    boolean modifyUserPhoto(UserDto userDto) throws Exception;
    // 회원정보 전송
    UserPetDto findUserInfo(Long userNumber) throws Exception;
    // 회원번호 전송
    UserDto findUserNumber(String userID) throws Exception;
    // 팔로우
    boolean addFollow(Long fromUserNumber, Long toUserNumber) throws Exception;
    // 언팔로우
    boolean removeFollow(Long fromUserNumber, Long toUserNumber) throws Exception;
    // 팔로우 리스트 조회
    List<UserDto> findFollowList(Long userNumber) throws Exception;
    // 팔로잉 리스트 조회
    List<UserDto> findFollowingList(Long userNumber) throws Exception;
}
