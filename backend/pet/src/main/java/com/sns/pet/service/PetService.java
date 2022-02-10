package com.sns.pet.service;

import com.sns.pet.dto.PetDto;

import java.util.List;

public interface PetService {
    // 모든 반려동물 목록 조회
    public List<PetDto> findAllPets(Long userNumber) throws Exception;
    // 특정 반려동물 상세 조회
    public PetDto findPet(Long userNumber, Long petNumber) throws Exception;
    // 유저의 반려동물 이름 중복 체크
    public boolean checkPetName(Long userNumber, String petName) throws Exception;
    // 반려동물 정보 저장
    public boolean addPet(PetDto petDto) throws Exception;
    // 반려동물 정보 삭제
    public boolean removePet(Long userNumber, Long petNumber) throws Exception;
}
