package com.sns.pet.dao;

import com.sns.pet.dto.JoinDto;
import com.sns.pet.dto.UserDto;
import com.sns.pet.dto.UserPetDto;
import org.apache.ibatis.annotations.Mapper;

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
}
