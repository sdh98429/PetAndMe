package com.sns.pet.service;

import com.sns.pet.dto.UserDto;

import java.util.Map;

public interface LoginService {
    UserDto findByIdAndPw(Map<String, String> user) throws Exception;
    UserDto findById(String userID) throws Exception;
}
