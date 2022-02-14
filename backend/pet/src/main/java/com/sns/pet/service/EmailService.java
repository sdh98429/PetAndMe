package com.sns.pet.service;

import org.apache.ibatis.annotations.Param;

public interface EmailService {

    String sendSimpleMessage(String userEmail) throws Exception;
    boolean addAuthKey(String userEmail, String authKey) throws Exception;
    boolean updateAuthKey(String userEmail) throws Exception;
    String findAuthKey(String userEmail) throws Exception;

}
