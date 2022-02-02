package com.sns.pet.service;

import com.sns.pet.dto.UserDto;

public interface UserService {

    public boolean addUser (UserDto userDto) throws Exception;
    public UserDto findUser(Long userNumber) throws Exception;
    public boolean modifyUser(UserDto userDto) throws Exception;
    public boolean removeUser(Long userNumber) throws Exception;

}
