package com.sns.pet.service;

import com.sns.pet.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    boolean addUser (UserDto userDto) throws Exception;
    UserDto findUser(Long userNumber) throws Exception;
    boolean modifyUser(UserDto userDto) throws Exception;
    boolean removeUser(Long userNumber) throws Exception;

    List selectUserInfo(Long userNumber) throws Exception;

}
