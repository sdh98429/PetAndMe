package com.sns.pet.dao;

import com.sns.pet.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface UserDao {

    public int insertUser(UserDto userDto) throws SQLException;
    public UserDto selectUser(long userNumber) throws SQLException;
    public int updateUser(UserDto userDto) throws SQLException;
    public int deleteUser(long userNumber) throws SQLException;

}
