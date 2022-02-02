package com.sns.pet.service;

import com.sns.pet.dto.UserDto;

public interface UserService {

    public boolean join (UserDto userDto) throws Exception;
    public UserDto userInfo(long userNumber) throws Exception;
    public boolean userModify(UserDto userDto) throws Exception;
    public boolean userRemove(long userNumber) throws Exception;

}
