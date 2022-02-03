package com.sns.pet.dao;

import com.sns.pet.dto.AnimalDto;
import com.sns.pet.dto.AnimalLikeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnimalDao {
    int insertAnimal(AnimalLikeDto animalLikeDto) throws Exception;
    int updateAnimal(AnimalLikeDto animalLikeDto) throws Exception;
    int deleteAnimal(@Param("userNumber") Long userNumber) throws Exception;

    List<AnimalLikeDto> selectFavAnimal(Long userNumber) throws Exception;
    List<AnimalDto> selectAnimal() throws Exception;
}
