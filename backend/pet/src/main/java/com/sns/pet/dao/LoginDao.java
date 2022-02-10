package com.sns.pet.dao;

import com.sns.pet.dto.UserDto;

import java.sql.SQLException;
import java.util.Map;

public interface LoginDao {

    Long selectByIdAndPw(Map<String, String> user) throws SQLException;
    Long selectById(String userID) throws SQLException;
}
