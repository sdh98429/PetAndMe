package com.sns.pet.service;

import org.apache.ibatis.annotations.Param;

public interface EmailService {
    String sendSimpleMessage(String userEmail) throws Exception;
}
