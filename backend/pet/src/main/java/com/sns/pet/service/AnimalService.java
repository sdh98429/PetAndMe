package com.sns.pet.service;

import com.sns.pet.dto.AnimalDto;
import com.sns.pet.dto.AnimalLikeDto;

import java.util.List;

public interface AnimalService {
    boolean addAnimal(AnimalLikeDto animalLikeDto) throws Exception;
    boolean modifyAnimal(AnimalLikeDto animalLikeDto) throws Exception;
    boolean removeAnimal(Long userNumber) throws Exception;

    List<AnimalLikeDto> findFavAnimal(Long userNumber) throws Exception;
    List<AnimalDto> findAnimal() throws Exception;
}
