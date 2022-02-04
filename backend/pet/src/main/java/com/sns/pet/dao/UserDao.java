package com.sns.pet.dao;

import com.sns.pet.dto.UserDto;
import com.sns.pet.dto.UserPetDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface UserDao {
    int insertUser(UserDto userDto) throws SQLException;
    UserDto selectUser(Long userNumber) throws SQLException;
    int updateUser(UserDto userDto) throws SQLException;
    int deleteUser(Long userNumber) throws SQLException;

    UserPetDto selectUserInfo(Long userNumber) throws SQLException;
}
