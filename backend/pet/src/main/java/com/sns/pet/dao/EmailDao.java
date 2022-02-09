package com.sns.pet.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

@Mapper
public interface EmailDao {

    // 이메일, 인증키 추가
    int insertAuthKey(@Param("userEmail") String userEmail, @Param("authKey") String authKey) throws SQLException;
    // 이메일 인증 성공시 업데이트
    int updateAuthKey(String userEmail) throws SQLException;
    // 인증키 조회
    String selectAuthKey(String userEmail) throws SQLException;

}
