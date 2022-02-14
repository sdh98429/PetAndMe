package com.sns.pet.dao;

import com.sns.pet.dto.UserDto;

import java.sql.SQLException;
import java.util.Map;

public interface LoginDao {

    // id와 pw를 이용한 user 조회
    UserDto selectByIdAndPw(Map<String, String> user) throws SQLException;
    // id를 이용한 user 조회
    UserDto selectById(String userID) throws SQLException;
}
