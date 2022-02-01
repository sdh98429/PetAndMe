package com.sns.pet.service;

import com.sns.pet.dto.petDto;

public interface PetService {
    // 특정 반려동물 상세 조회
    public petDto findPet(Long userNumber, int petNumber) throws Exception;
    // 반려동물 정보 저장
    public boolean addPet(petDto petDto) throws Exception;
    // 반려동물 정보 수정
    public boolean modifyPet(petDto petDto) throws Exception;
    // 반려동물 정보 삭제
    public boolean removePet(Long userNumber, int petNumber) throws Exception;
}
