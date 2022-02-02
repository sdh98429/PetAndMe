package com.sns.pet.dao;

import com.sns.pet.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface UserDao {

    public int join(UserDto userDto) throws SQLException;
    public UserDto userInfo(long userNumber) throws SQLException;
    public int userModify(UserDto userDto) throws SQLException;
    public int userRemove(long userNumber) throws SQLException;

}
