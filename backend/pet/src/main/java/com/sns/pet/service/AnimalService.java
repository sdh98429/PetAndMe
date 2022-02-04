package com.sns.pet.service;

import com.sns.pet.dto.AnimalDto;
import com.sns.pet.dto.AnimalLikeDto;

import java.util.List;

public interface AnimalService {
    boolean addAnimal(List<AnimalLikeDto> animalLikeDtoList) throws Exception;
    boolean modifyAnimal(List<AnimalLikeDto> animalLikeDtoList) throws Exception;
    boolean removeAnimal(Long userNumber) throws Exception;

    List<AnimalLikeDto> findFavAnimal(Long userNumber) throws Exception;
    List<AnimalDto> findAnimal() throws Exception;
}
