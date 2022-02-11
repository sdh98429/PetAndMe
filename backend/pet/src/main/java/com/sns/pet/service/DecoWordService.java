package com.sns.pet.service;

import java.util.List;

public interface DecoWordService {
    // 닉네임 추천
    List<String> makeNickName(int animalNumber) throws Exception;
}