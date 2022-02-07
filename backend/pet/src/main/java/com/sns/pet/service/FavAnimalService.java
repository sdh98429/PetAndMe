package com.sns.pet.service;

import com.sns.pet.dto.AnimalDto;
import com.sns.pet.dto.FavAnimalDto;

import java.util.List;

public interface FavAnimalService {
    // 선호동물 등록
    boolean addFavAnimal(List<FavAnimalDto> favAnimalDtoList) throws Exception;
    // 선호동물 수정(삭제 후, 재등록)
    boolean modifyFavAnimal(List<FavAnimalDto> favAnimalDtoList) throws Exception;
    // 선호동물 삭제
    boolean removeFavAnimal(Long userNumber) throws Exception;
    // 선호동물 조회
    List<FavAnimalDto> findFavAnimal(Long userNumber) throws Exception;
    // 동물목록 조회
    List<AnimalDto> findAnimal() throws Exception;
}
