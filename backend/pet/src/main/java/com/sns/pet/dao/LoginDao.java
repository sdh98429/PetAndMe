package com.sns.pet.dao;

import com.sns.pet.dto.UserDto;

import java.sql.SQLException;
import java.util.Map;

public interface LoginDao {

    UserDto selectByIdAndPw(Map<String, String> user) throws SQLException;
    UserDto selectById(String userID) throws SQLException;
}
