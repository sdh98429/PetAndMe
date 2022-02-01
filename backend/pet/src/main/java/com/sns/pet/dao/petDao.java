package com.sns.pet.dao;

import com.sns.pet.dto.petDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

@Mapper
public interface petDao {
    // 모든 반려동물 조회
//   List<petDto> selectAllPets() throws SQLException;
    // 특정 반려동물 상세조회
    petDto selectPet(@Param("userNumber") Long userNumber, @Param("petNumber") int petNumber) throws SQLException;
    // 반려동물 정보 저장
    int insertPet(petDto petDto) throws SQLException;
    // 반려동물 정보 수정
    int updatePet(petDto petDto) throws SQLException;
    // 반려동물 정보 삭제
    int deletePet(@Param("userNumber") Long userNumber, @Param("petNumber") int petNumber) throws SQLException;
}
