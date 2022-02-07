package com.sns.pet.dao;

import com.sns.pet.dto.AnimalDto;
import com.sns.pet.dto.FavAnimalDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface FavAnimalDao {
    // 선호동물 정보 저장
    int insertFavAnimal(List<FavAnimalDto> favAnimalDtoList) throws SQLException;
    // 선호동물 정보 삭제
    int deleteFavAnimal(@Param("userNumber") Long userNumber) throws SQLException;
    // 선호동물 조회
    List<FavAnimalDto> selectFavAnimal(Long userNumber) throws SQLException;
    // 동물 조회
    List<AnimalDto> selectAnimal() throws SQLException;
}
