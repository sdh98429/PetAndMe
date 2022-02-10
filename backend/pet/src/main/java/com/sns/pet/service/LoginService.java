package com.sns.pet.service;

import com.sns.pet.dto.UserDto;

import java.util.Map;

public interface LoginService {
    Long findByIdAndPw(Map<String, String> user) throws Exception;
    Long findById(String userID) throws Exception;
}
