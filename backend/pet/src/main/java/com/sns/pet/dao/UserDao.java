package com.sns.pet.dao;

import com.sns.pet.dto.JoinDto;
import com.sns.pet.dto.UserDto;
import com.sns.pet.dto.UserPetDto;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface UserDao {
    // 회원가입
    int insertUser(JoinDto joinDto) throws SQLException;
    // 회원조회
    UserDto selectUser(Long userNumber) throws SQLException;
    // 회원정보 수정
    int updateUser(UserDto userDto) throws SQLException;
    // 회원정보 삭제
    int deleteUser(Long userNumber) throws SQLException;
    // 회원정보 전송
    UserPetDto selectUserInfo(Long userNumber) throws SQLException;
    // 회원번호 조회
    UserDto selectUserNumber(String userID) throws SQLException;
    // 팔로우
    int insertFollow(@Param("fromUserNumber") Long fromUserNumber, @Param("toUserNumber") Long toUserNumber) throws SQLException;
    // 언팔로우
    int deleteFollow(@Param("fromUserNumber") Long fromUserNumber, @Param("toUserNumber") Long toUserNumber) throws SQLException;
    // 팔로우 리스트 조회
    List<UserDto> selectFollowList(Long userNumber) throws SQLException;
    // 팔로잉 리스트 조회
    List<UserDto> selectFollowingList(Long userNumber) throws SQLException;
}
